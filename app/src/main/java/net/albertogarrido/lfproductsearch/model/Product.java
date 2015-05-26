package net.albertogarrido.lfproductsearch.model;


import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Product implements Parcelable {

    private String id;
    private String name;
    private String indexableName;
    private String description;
    private HashMap<String, String> images = new HashMap<String, String>();
    private ArrayList<PointF> location;

    public Product(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndexableName() {
        return indexableName;
    }

    public void setIndexableName(String indexableName) {
        this.indexableName = indexableName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HashMap<String, String> getImages() {
        return images;
    }

    public void setImages(HashMap<String, String> images) {
        this.images = images;
    }

    public ArrayList<PointF> getLocation() {
        return location;
    }

    public void setLocation(ArrayList<PointF> location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public Product(Parcel in){
        readFromParcel(in);
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(indexableName);
        dest.writeString(description);
        dest.writeInt(images.size());
        for(Map.Entry<String,String> entry : images.entrySet()){
            dest.writeString(entry.getKey());
            dest.writeString(entry.getValue());
        }
        dest.writeTypedList(location);
    }

    public void readFromParcel(Parcel in){
        this.id = in.readString();
        this.name = in.readString();
        this.indexableName = in.readString();
        this.description = in.readString();
        int size = in.readInt();
        for(int i = 0; i < size; i++){
            String key = in.readString();
            String value = in.readString();
            images.put(key, value);
        }
        location = new ArrayList<>();
        in.readTypedList(location, PointF.CREATOR);
    }

    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>(){

        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

}
