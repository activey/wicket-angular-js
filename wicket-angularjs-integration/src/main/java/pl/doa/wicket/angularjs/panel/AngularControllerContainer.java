/**
 * 
 */
package pl.doa.wicket.angularjs.panel;

import org.apache.wicket.ajax.json.JSONException;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;

import pl.doa.wicket.angularjs.behavior.AngularControllerBehavior;
import pl.doa.wicket.angularjs.serializer.IModelSerializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * @author activey
 * 
 */
public class AngularControllerContainer<T extends Object> extends
		WebMarkupContainer implements IModelSerializer<T> {

	private static final long serialVersionUID = 1L;

	private IModelSerializer<T> modelSerializer;

	public AngularControllerContainer(String id, IModel<T> model,
			IModelSerializer<T> modelSerializer) {
		super(id, model);
		this.modelSerializer = modelSerializer;
	}

	public AngularControllerContainer(String id) {
		super(id);
	}

	public AngularControllerContainer(String id,
			IModelSerializer<T> modelSerializer) {
		super(id);
		this.modelSerializer = modelSerializer;
	}

	@Override
	protected final void onInitialize() {
		super.onInitialize();

		add(new AngularControllerBehavior());
		onInitializeContainer();
	}

	protected void onInitializeContainer() {

	}

	@Override
	public JsonElement serialize(IModel<T> model) throws JSONException {
		if (this.modelSerializer == null) {
			return new JsonObject();
		}
		return modelSerializer.serialize(model);
	}

	public final void setModel(IModel<T> model) {
		setDefaultModel(model);
	}

	public final IModel<T> getModel() {
		return (IModel<T>) getDefaultModel();
	}

	public final void setModelObject(T object) {
		setDefaultModelObject(object);
	}

	public final T getModelObject() {
		return (T) getDefaultModelObject();
	}

	public void setModelSerializer(IModelSerializer<T> modelSerializer) {
		this.modelSerializer = modelSerializer;
	}

	@Override
	public T deserialize(JsonObject json) throws JSONException {
		return null;
	}
}
