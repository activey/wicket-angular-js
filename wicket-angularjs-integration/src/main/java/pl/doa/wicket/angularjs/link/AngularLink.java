package pl.doa.wicket.angularjs.link;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;

public class AngularLink extends WebMarkupContainer {

	public AngularLink(String id) {
		super(id);
	}

	@Override
	protected final void onInitialize() {
		super.onInitialize();

		add(new AttributeModifier("ng:click", getControllerFunctionName()
				+ "()"));
	}

	protected String getControllerFunctionName() {
		return "reload";
	}

	@Override
	protected void onComponentTag(final ComponentTag tag) {
		checkComponentTag(tag, "a");

	}

}
