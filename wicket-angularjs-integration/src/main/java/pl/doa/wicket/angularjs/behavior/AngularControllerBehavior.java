package pl.doa.wicket.angularjs.behavior;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.json.JSONException;
import org.apache.wicket.behavior.AbstractAjaxBehavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.servlet.ServletWebRequest;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.handler.TextRequestHandler;
import org.apache.wicket.util.template.PackageTextTemplate;
import pl.doa.wicket.angularjs.RequestUtils;
import pl.doa.wicket.angularjs.resources.AngularResources;
import pl.doa.wicket.angularjs.serializer.IModelSerializer;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class AngularControllerBehavior extends AbstractAjaxBehavior {

	private String controllerName;

	@Override
	protected void onComponentRendered() {
		final Component component = getComponent();
		JavaScriptHeaderItem
				.forScript(
						new PackageTextTemplate(AngularResources.class,
								"template-controller.js").asString(new HashMap<String, Object>() {
							{
								put("controller_name",
										AngularControllerBehavior.this.controllerName);
								put("component_json_url",
										AngularControllerBehavior.this
												.getCallbackUrl());
							}
						}), "angular-controller-" + component.getId()).render(
						getComponent().getResponse());
	}

	@Override
	public void onComponentTag(ComponentTag tag) {
		Component component = getComponent();
		if (component instanceof IAngularController) {
			IAngularController controller = (IAngularController) component;
			this.controllerName = controller.getControllerName();
		} else {
			this.controllerName = "Controller_" + tag.getId();
		}
		tag.getAttributes().put("ng:controller", this.controllerName);
	}

	@Override
	public void onRequest() {
		Component component = getComponent();
		IModel<?> componentModel = component.getDefaultModel();

		// getting http request reference
		RequestCycle requestCycle = RequestCycle.get();
		ServletWebRequest webRequest = (ServletWebRequest) requestCycle
				.getRequest();
		HttpServletRequest request = webRequest.getContainerRequest();
		String httpMethod = request.getMethod();

		if ("POST".equalsIgnoreCase(httpMethod)
				&& (component instanceof IModelSerializer)) {

			// updating model value
			String requestBody;
			try {
				requestBody = RequestUtils.readRequestBody(request);

				IModelSerializer<?> deserializer = (IModelSerializer<?>) component;
				Object modelValue = deserializer
						.deserialize((JsonObject) new JsonParser()
								.parse(requestBody));
				component.setDefaultModelObject(modelValue);
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}

			// passing action to controler
			if (component instanceof IAngularController) {
				IAngularController controller = (IAngularController) component;
				controller.invoke(requestCycle);
				return;
			}
		}

		if (component instanceof IModelSerializer) {
			IModelSerializer serializer = (IModelSerializer) component;
			try {
				JsonElement serialized = serializer.serialize(componentModel);
				if (serialized == null) {
					return;
				}
				requestCycle
						.scheduleRequestHandlerAfterCurrent(new TextRequestHandler(
								"application/json", "UTF-8", serialized.toString()));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

	}

}
