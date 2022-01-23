package de.fhswf.moa.surveys.list.item;

import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

public interface QuestionResultItem {

    @Nullable
    JSONObject getResult() throws JSONException;

}
