package pl.doa.wicket.angularjs.label;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import static pl.doa.wicket.angularjs.serializer.impl.DefaultObjectSerializer.getAngularModelProperty;

public class AngularLabel extends Label {

	private IModel<String> angularPropertyModel;

	public AngularLabel(String id, IModel<String> angularPropertyModel) {
		super(id);
		this.angularPropertyModel = angularPropertyModel;
	}
	
	public AngularLabel(String id, String angularProperty) {
		this(id, new Model<String>(angularProperty));
	}

	public AngularLabel(String id) {
		this(id, getAngularModelProperty());
	}

	@Override
	public void onComponentTagBody(MarkupStream markupStream,
			ComponentTag openTag) {
		replaceComponentTagBody(markupStream, openTag, "{{data."
				+ angularPropertyModel.getObject() + "}}");
	}
}
