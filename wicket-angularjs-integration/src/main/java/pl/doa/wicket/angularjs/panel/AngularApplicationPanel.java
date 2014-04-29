/**
 * 
 */
package pl.doa.wicket.angularjs.panel;

import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.resource.TextTemplateResourceReference;

import pl.doa.wicket.angularjs.resources.AngularResources;

/**
 * @author activey
 * 
 */
public class AngularApplicationPanel extends Panel {

	public AngularApplicationPanel(String id) {
		super(id);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		add(new AttributeModifier("ng:app", "angular_app_" + getId()));
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		response.render(JavaScriptHeaderItem
				.forReference(new PackageResourceReference(
						AngularResources.class, "angular.min.js")));
		response.render(JavaScriptHeaderItem
				.forReference(new PackageResourceReference(
						AngularResources.class, "angular-resource.min.js")));

		response.render(JavaScriptHeaderItem
				.forReference(new TextTemplateResourceReference(
						AngularResources.class, "template-application.js",
						new AbstractReadOnlyModel<Map<String, Object>>() {

							@Override
							public Map<String, Object> getObject() {
								return new HashMap<String, Object>() {
									{
										put("app_id",
												AngularApplicationPanel.this
														.getId());
									}
								};
							}

						})));

	}
}
