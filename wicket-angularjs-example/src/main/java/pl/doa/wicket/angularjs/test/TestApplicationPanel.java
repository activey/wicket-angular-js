package pl.doa.wicket.angularjs.test;

import org.apache.wicket.model.AbstractReadOnlyModel;
import pl.doa.wicket.angularjs.label.AngularLabel;
import pl.doa.wicket.angularjs.link.AngularLink;
import pl.doa.wicket.angularjs.panel.AngularApplicationPanel;
import pl.doa.wicket.angularjs.panel.AngularControllerContainer;
import pl.doa.wicket.angularjs.serializer.impl.DefaultStringSerializer;

import java.util.ArrayList;
import java.util.Collection;

public class TestApplicationPanel extends AngularApplicationPanel {

	private static final long serialVersionUID = 1L;

	private Collection<TestObject> list;

    private TestObject testObject;

	public TestApplicationPanel() {
		super("app");
		this.list = new ArrayList<TestObject>() {
			{
				for (int i = 1; i < 5; i++) {
					add(new TestObject("test-a-" + i, "test-b-" + i));
				}

			}
		};
        this.testObject = new TestObject("aaa", "bbb");
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		add(new AngularControllerContainer<String>("panel_hello",
				new AbstractReadOnlyModel<String>() {

					@Override
					public String getObject() {
						return "Hello AngularJS! Current time: "
								+ System.currentTimeMillis();
					}
				}, new DefaultStringSerializer()) {
			protected void onInitializeContainer() {
				add(new AngularLabel("test_label",
						new AbstractReadOnlyModel<String>() {

							@Override
							public String getObject() {
								return "data";
							}
						}));
				add(new AngularLink("link_refresh"));
			};
		});

		add(new TestContainer("panel_list", list));

		add(new TestForm("test_form", this.testObject));

	}

}
