/**
 * 
 */
package pl.doa.wicket.angularjs.serializer.impl;

import java.io.Serializable;
import java.util.Collection;

import org.apache.wicket.ajax.json.JSONException;
import org.apache.wicket.model.IModel;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * @author activey
 * @param <L>
 * 
 */
public class DefaultCollectionSerializer<T extends Object> extends
		DefaultObjectSerializer<Collection<T>> implements Serializable {

	@Override
	public JsonObject serialize(IModel<Collection<T>> model)
			throws JSONException {
		Collection<T> list = model.getObject();

		if (list.size() == 0) {
			return null;
		}
		JsonObject json = new JsonObject();

		Gson serializer = new Gson();
		JsonArray array = new JsonArray();
		for (T t : list) {
			array.add(serializer.toJsonTree(t));
		}
		json.add("elements", array);
		return json;
	}

	@Override
	public Collection<T> deserialize(JsonObject json) throws JSONException {
		JsonArray array = json.get("elements").getAsJsonArray();
		Gson serializer = new Gson();
		return serializer.fromJson(array, getTypeClass());
	}

	@Override
	public Class<Collection<T>> getTypeClass() {
		return null;
	}

}
