/**
 * 
 */
package pl.doa.wicket.angularjs.form;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;

/**
 * @author activey
 * 
 */
public class AngularSubmitButton extends WebMarkupContainer {

	public AngularSubmitButton(String id) {
		super(id);
	}

	@Override
	protected final void onInitialize() {
		super.onInitialize();

		onInitializeButton();
	}

	protected void onInitializeButton() {

	}

	@Override
	protected void onComponentTag(ComponentTag tag) {
		tag.getAttributes().put("ng:click", "push()");
	}
}
