package com.example.harsh.intact_test;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Products implements Parcelable {
    int pid,pqty;
    String ptitle,pbrand,pdesc,pimg,pheight,pwidth,pdepth,sdesc;
    double pprice;
    ArrayList<String> colors;

    public Products(int pid, int pqty, String ptitle, String pbrand, String pdesc, String pimg, String pheight, String pwidth, String pdepth, String sdesc, double pprice, ArrayList<String> colors) {
        this.pid = pid;
        this.pqty = pqty;
        this.ptitle = ptitle;
        this.pbrand = pbrand;
        this.pdesc = pdesc;
        this.pimg = pimg;
        this.pheight = pheight;
        this.pwidth = pwidth;
        this.pdepth = pdepth;
        this.sdesc = sdesc;
        this.pprice = pprice;
        this.colors = colors;
    }

    protected Products(Parcel in) {
        pid = in.readInt();
        pqty = in.readInt();
        ptitle = in.readString();
        pbrand = in.readString();
        pdesc = in.readString();
        pimg = in.readString();
        pheight = in.readString();
        pwidth = in.readString();
        pdepth = in.readString();
        sdesc = in.readString();
        pprice = in.readDouble();
        colors = in.createStringArrayList();
    }

    public static final Creator<Products> CREATOR = new Creator<Products>() {
        @Override
        public Products createFromParcel(Parcel in) {
            return new Products(in);
        }

        @Override
        public Products[] newArray(int size) {
            return new Products[size];
        }
    };

    public ArrayList<String> getColors() {
        return colors;
    }

    public void setColors(ArrayList<String> colors) {
        this.colors = colors;
    }

    public int getPid() {
        return pid;
    }

    public String getSdesc() {
        return sdesc;
    }

    public void setSdesc(String sdesc) {
        this.sdesc = sdesc;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getPqty() {
        return pqty;
    }

    public void setPqty(int pqty) {
        this.pqty = pqty;
    }

    public String getPtitle() {
        return ptitle;
    }

    public void setPtitle(String ptitle) {
        this.ptitle = ptitle;
    }

    public String getPbrand() {
        return pbrand;
    }

    public void setPbrand(String pbrand) {
        this.pbrand = pbrand;
    }

    public String getPdesc() {
        return pdesc;
    }

    public void setPdesc(String pdesc) {
        this.pdesc = pdesc;
    }

    public String getPimg() {
        return pimg;
    }

    public void setPimg(String pimg) {
        this.pimg = pimg;
    }

    public String getPheight() {
        return pheight;
    }

    public void setPheight(String pheight) {
        this.pheight = pheight;
    }

    public String getPwidth() {
        return pwidth;
    }

    public void setPwidth(String pwidth) {
        this.pwidth = pwidth;
    }

    public String getPdepth() {
        return pdepth;
    }

    public void setPdepth(String pdepth) {
        this.pdepth = pdepth;
    }

    public double getPprice() {
        return pprice;
    }

    public void setPprice(double pprice) {
        this.pprice = pprice;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(pid);
        parcel.writeInt(pqty);
        parcel.writeString(ptitle);
        parcel.writeString(pbrand);
        parcel.writeString(pdesc);
        parcel.writeString(pimg);
        parcel.writeString(pheight);
        parcel.writeString(pwidth);
        parcel.writeString(pdepth);
        parcel.writeString(sdesc);
        parcel.writeDouble(pprice);
        parcel.writeStringList(colors);
    }
}
