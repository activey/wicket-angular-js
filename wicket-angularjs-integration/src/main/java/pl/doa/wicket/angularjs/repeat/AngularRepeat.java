/**
 * 
 */
package pl.doa.wicket.angularjs.repeat;

import java.util.Collection;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.json.JSONException;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;

import pl.doa.wicket.angularjs.serializer.IModelSerializer;
import pl.doa.wicket.angularjs.serializer.impl.DefaultCollectionSerializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * @author activey
 * 
 */
public class AngularRepeat<T extends Object> extends WebMarkupContainer
		implements IModelSerializer<Collection<T>> {

	private IModelSerializer<Collection<T>> modelSerializer;

	public AngularRepeat(String id, IModel<Collection<T>> collectionModel,
			IModelSerializer<Collection<T>> modelSerializer) {
		super(id, collectionModel);
		this.modelSerializer = modelSerializer;
	}

	public AngularRepeat(String id, IModel<Collection<T>> collectionModel) {
		this(id, collectionModel, new DefaultCollectionSerializer<T>());
	}

	@Override
	protected final void onInitialize() {
		super.onInitialize();
		add(new AttributeModifier("ng:repeat", "data in data.elements"));

		onInitializeRepeat();
	}

	protected void onInitializeRepeat() {

	}

	@Override
	public JsonElement serialize(IModel<Collection<T>> model)
			throws JSONException {
		if (this.modelSerializer == null) {
			return new DefaultCollectionSerializer<T>().serialize(model);
		}
		return modelSerializer.serialize(model);
	}

	@Override
	public Collection<T> deserialize(JsonObject json) throws JSONException {
		return null;
	}

}
