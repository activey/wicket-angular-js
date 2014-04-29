/**
 * 
 */
package pl.doa.wicket.angularjs.test;

import java.util.Collection;

import org.apache.wicket.model.util.CollectionModel;

import pl.doa.wicket.angularjs.label.AngularLabel;
import pl.doa.wicket.angularjs.panel.AngularControllerContainer;
import pl.doa.wicket.angularjs.repeat.AngularRepeat;

/**
 * @author activey
 * 
 */
public class TestContainer extends
		AngularControllerContainer<Collection<TestObject>> {

	private Collection<TestObject> list;

	public TestContainer(String id, Collection<TestObject> list) {
		super(id);
		this.list = list;
		setModel(new CollectionModel<TestObject>(list));
	}

	@Override
	protected void onInitializeContainer() {
		add(new AngularRepeat<TestObject>("element_placeholder", getModel()) {

			@Override
			protected void onInitializeRepeat() {
				add(new AngularLabel("test1_placeholder", "test1"));
				add(new AngularLabel("test2_placeholder", "test2"));
			}
		});
	}

}
