/**
 * 
 */
package pl.doa.wicket.angularjs.form;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.model.IModel;

/**
 * @author activey
 * 
 */
public class AngularField extends WebComponent {

	private IModel<String> fieldModel;

	public AngularField(String id, IModel<String> fieldModel) {
		super(id);
		this.fieldModel = fieldModel;
	}

	@Override
	protected void onComponentTag(ComponentTag tag) {
		checkComponentTag(tag, "input");

		super.onComponentTag(tag);
		tag.getAttributes().put("ng:model", "data." + fieldModel.getObject());
	}
}
