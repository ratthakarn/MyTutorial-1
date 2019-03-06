package masterung.th.in.androidthai.mytutorial;

import android.os.Parcel;
import android.os.Parcelable;

public class ListVideoModel implements Parcelable {

    private String Image, Name;

    public ListVideoModel() {
    }

    public ListVideoModel(String image, String name) {
        Image = image;
        Name = name;
    }

    protected ListVideoModel(Parcel in) {
        Image = in.readString();
        Name = in.readString();
    }

    public static final Creator<ListVideoModel> CREATOR = new Creator<ListVideoModel>() {
        @Override
        public ListVideoModel createFromParcel(Parcel in) {
            return new ListVideoModel(in);
        }

        @Override
        public ListVideoModel[] newArray(int size) {
            return new ListVideoModel[size];
        }
    };

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Image);
        dest.writeString(Name);
    }
}
