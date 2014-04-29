/**
 * 
 */
package pl.doa.wicket.angularjs.form;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.wicket.ajax.json.JSONException;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.cycle.RequestCycle;
import pl.doa.wicket.angularjs.behavior.AngularControllerBehavior;
import pl.doa.wicket.angularjs.behavior.IAngularController;
import pl.doa.wicket.angularjs.serializer.IModelSerializer;

import java.io.Serializable;

/**
 * @author activey
 * 
 */
public abstract class AngularForm<T extends Serializable> extends
		WebMarkupContainer implements IModelSerializer<T>, IAngularController {

	private IModelSerializer<T> modelSerializer;

	public AngularForm(String id, IModel<T> formInputModel,
			IModelSerializer<T> modelSerializer) {
		super(id, formInputModel);
		this.modelSerializer = modelSerializer;
	}

	public AngularForm(String id, T formInput,
			IModelSerializer<T> modelSerializer) {
		super(id, new Model<Serializable>(formInput));
		this.modelSerializer = modelSerializer;
	}

	@Override
	protected final void onInitialize() {
		super.onInitialize();

		add(new AngularControllerBehavior());
		onInitializeForm();
	}

	protected void onInitializeForm() {

	}

	@Override
	protected void onComponentTag(final ComponentTag tag) {
		checkComponentTag(tag, "form");

		super.onComponentTag(tag);
	}

	@Override
	public JsonElement serialize(IModel<T> model) throws JSONException {
		return modelSerializer.serialize(model);
	}

	@Override
	public String getControllerName() {
		return "Form_" + getId();
	}

	@Override
	public final void invoke(RequestCycle requestCycle) {
		processResponse(new AngularResponse(requestCycle));
	}

	protected void processResponse(AngularResponse angularResponse) {
		
	}

	public abstract Class<T> getTypeClass();

	@Override
	public T deserialize(JsonObject json) throws JSONException {
		return modelSerializer.deserialize(json);
	}

	public final IModel<T> getModel() {
		return (IModel<T>) getDefaultModel();
	}

	public final T getModelObject() {
		return (T) getDefaultModelObject();
	}
}
