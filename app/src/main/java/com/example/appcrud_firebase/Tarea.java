package com.example.appcrud_firebase;

import android.os.Parcel;
import android.os.Parcelable;

public class Tarea implements Parcelable {

    private int id;
    private String nombre;

    public Tarea() {
    }

    public Tarea(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    //AQUI SE DEFINE EL DATO A MOSTRAR EN EL LIST VIEW
    @Override
    public String toString() {
        return nombre;
    }
    //AQUI SE DEFINEN LOS PARCELABRES SON SOLO EN CASO DE QUE SE QUIERAN MANDAR OBJETOS DE UNA VENTANA A OTRA
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.nombre);
    }

    public void readFromParcel(Parcel source) {
        this.id = source.readInt();
        this.nombre = source.readString();
    }

    protected Tarea(Parcel in) {
        this.id = in.readInt();
        this.nombre = in.readString();
    }

    public static final Parcelable.Creator<Tarea> CREATOR = new Parcelable.Creator<Tarea>() {
        @Override
        public Tarea createFromParcel(Parcel source) {
            return new Tarea(source);
        }

        @Override
        public Tarea[] newArray(int size) {
            return new Tarea[size];
        }
    };

}