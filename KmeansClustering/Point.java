package KmeansClustering;

public class Point {
    private double val1;
    private double val2;
    private double val3;
    private double val4;
    private double val5;
    private double val6;
    private double val7;
    private double val8;
    private double val9;
    private double val10;
    private double val11;
    private double val12;
    private double val13;
    private double val14;
    private double val15;
    private double val16;
    private double val17;
    private double val18;
    private double val19;
    private double val20;
    private double val21;
    private double val22;
    private double val23;
    private double val24;
    private double val25;
    private double val26;
    private double val27;
    private double val28;
    private double val29;
    private double val30;
    private double val31;
    private double val32;
    private double val33;
    private double val34;
    private double val35;
    private double val36;
    private double val37;
    private double val38;
    private double val39;
    private double val40;
    private double val41;
    private double val42;
    private double val43;
    private double val44;
    private double val45;
    private double val46;
    private double val47;
    private double val48;
    private double val49;
    private double val50;
    private double val51;
    private double val52;
    private double val53;
    private double val54;
    private double val55;
    private double val56;
    private double val57;
    private double val58;
    private double val59;
    private double val60;
    private int cluster;

    public Point(double val1, double val2, double val3, double val4, double val5, double val6, double val7, double val8, double val9, double val10, 
        double val11, double val12, double val13, double val14, double val15, double val16, double val17, double val18, double val19, double val20, 
        double val21, double val22, double val23, double val24, double val25, double val26, double val27, double val28, double val29, double val30, 
        double val31, double val32, double val33, double val34, double val35, double val36, double val37, double val38, double val39, double val40, 
        double val41, double val42, double val43, double val44, double val45, double val46, double val47, double val48, double val49, double val50, 
        double val51, double val52, double val53, double val54, double val55, double val56, double val57, double val58, double val59, double val60){
        this.val1 = val1;
        this.val2 = val2;
        this.val3 = val3;
        this.val4 = val4;
        this.val5 = val5;
        this.val6 = val6;
        this.val7 = val7;
        this.val8 = val8;
        this.val9 = val9;
        this.val10 = val10;
        this.val11 = val11;
        this.val12 = val12;
        this.val13 = val13;
        this.val14 = val14;
        this.val15 = val15;
        this.val16 = val16;
        this.val17 = val17;
        this.val18 = val18;
        this.val19 = val19;
        this.val20 = val20;
        this.val21 = val21;
        this.val22 = val22;
        this.val23 = val23;
        this.val24 = val24;
        this.val25 = val25;
        this.val26 = val26;
        this.val27 = val27;
        this.val28 = val28;
        this.val29 = val29;
        this.val30 = val30;
        this.val31 = val31;
        this.val32 = val32;
        this.val33 = val33;
        this.val34 = val34;
        this.val35 = val35;
        this.val36 = val36;
        this.val37 = val37;
        this.val38 = val38;
        this.val39 = val39;
        this.val40 = val40;
        this.val41 = val41;
        this.val42 = val42;
        this.val43 = val43;
        this.val44 = val44;
        this.val45 = val45;
        this.val46 = val46;
        this.val47 = val47;
        this.val48 = val48;
        this.val49 = val49;
        this.val50 = val50;
        this.val51 = val51;
        this.val52 = val52;
        this.val53 = val53;
        this.val54 = val54;
        this.val55 = val55;
        this.val56 = val56;
        this.val57 = val57;
        this.val58 = val58;
        this.val59 = val59;
        this.val60 = val60;
    }

    public Point(double[] array) {
        this.val1 = array[0];
        this.val2 = array[1];
        this.val3 = array[2];
        this.val4 = array[3];
        this.val5 = array[4];
        this.val6 = array[5];
        this.val7 = array[6];
        this.val8 = array[7];
        this.val9 = array[8];
        this.val10 = array[9];
        this.val11 = array[10];
        this.val12 = array[11];
        this.val13 = array[12];
        this.val14 = array[13];
        this.val15 = array[14];
        this.val16 = array[15];
        this.val17 = array[16];
        this.val18 = array[17];
        this.val19 = array[18];
        this.val20 = array[19];
        this.val21 = array[20];
        this.val22 = array[21];
        this.val23 = array[22];
        this.val24 = array[23];
        this.val25 = array[24];
        this.val26 = array[25];
        this.val27 = array[26];
        this.val28 = array[27];
        this.val29 = array[28];
        this.val30 = array[29];
        this.val31 = array[30];
        this.val32 = array[31];
        this.val33 = array[32];
        this.val34 = array[33];
        this.val35 = array[34];
        this.val36 = array[35];
        this.val37 = array[36];
        this.val38 = array[37];
        this.val39 = array[38];
        this.val40 = array[39];
        this.val41 = array[40];
        this.val42 = array[41];
        this.val43 = array[42];
        this.val44 = array[43];
        this.val45 = array[44];
        this.val46 = array[45];
        this.val47 = array[46];
        this.val48 = array[47];
        this.val49 = array[48];
        this.val50 = array[49];
        this.val51 = array[50];
        this.val52 = array[51];
        this.val53 = array[52];
        this.val54 = array[53];
        this.val55 = array[54];
        this.val56 = array[55];
        this.val57 = array[56];
        this.val58 = array[57];
        this.val59 = array[58];
        this.val60 = array[59];
    }

    public double getVal1() {
        return this.val1;
    }
    public double getVal2() {
        return this.val2;
    }
    public double getVal3() {
        return this.val3;
    }
    public double getVal4() {
        return this.val4;
    }
    public double getVal5() {
        return this.val5;
    }
    public double getVal6() {
        return this.val6;
    }
    public double getVal7() {
        return this.val7;
    }
    public double getVal8() {
        return this.val8;
    }
    public double getVal9() {
        return this.val9;
    }
    public double getVal10() {
        return this.val10;
    }
    public double getVal11() {
        return this.val11;
    }
    public double getVal12() {
        return this.val12;
    }
    public double getVal13() {
        return this.val13;
    }
    public double getVal14() {
        return this.val14;
    }
    public double getVal15() {
        return this.val15;
    }
    public double getVal16() {
        return this.val16;
    }
    public double getVal17() {
        return this.val17;
    }
    public double getVal18() {
        return this.val18;
    }
    public double getVal19() {
        return this.val19;
    }
    public double getVal20() {
        return this.val20;
    }
    public double getVal21() {
        return this.val21;
    }
    public double getVal22() {
        return this.val22;
    }
    public double getVal23() {
        return this.val23;
    }
    public double getVal24() {
        return this.val24;
    }
    public double getVal25() {
        return this.val25;
    }
    public double getVal26() {
        return this.val26;
    }
    public double getVal27() {
        return this.val27;
    }
    public double getVal28() {
        return this.val28;
    }
    public double getVal29() {
        return this.val29;
    }
    public double getVal30() {
        return this.val30;
    }
    public double getVal31() {
        return this.val31;
    }
    public double getVal32() {
        return this.val32;
    }
    public double getVal33() {
        return this.val33;
    }
    public double getVal34() {
        return this.val34;
    }
    public double getVal35() {
        return this.val35;
    }
    public double getVal36() {
        return this.val36;
    }
    public double getVal37() {
        return this.val37;
    }
    public double getVal38() {
        return this.val38;
    }
    public double getVal39() {
        return this.val39;
    }
    public double getVal40() {
        return this.val40;
    }
    public double getVal41() {
        return this.val41;
    }
    public double getVal42() {
        return this.val42;
    }
    public double getVal43() {
        return this.val43;
    }
    public double getVal44() {
        return this.val44;
    }
    public double getVal45() {
        return this.val45;
    }
    public double getVal46() {
        return this.val46;
    }
    public double getVal47() {
        return this.val47;
    }
    public double getVal48() {
        return this.val48;
    }
    public double getVal49() {
        return this.val49;
    }
    public double getVal50() {
        return this.val50;
    }
    public double getVal51() {
        return this.val51;
    }
    public double getVal52() {
        return this.val52;
    }
    public double getVal53() {
        return this.val53;
    }
    public double getVal54() {
        return this.val54;
    }
    public double getVal55() {
        return this.val55;
    }
    public double getVal56() {
        return this.val56;
    }
    public double getVal57() {
        return this.val57;
    }
    public double getVal58() {
        return this.val58;
    }
    public double getVal59() {
        return this.val59;
    }
    public double getVal60() {
        return this.val60;
    }

    public void setCluster(int n) {
        this.cluster = n;
    }
    
    public int getCluster() {
        return this.cluster;
    }

    protected static double distanceToCentroid(Point p, Point centroid) {
        return Math.sqrt(Math.pow((centroid.getVal1() - p.getVal1()), 2) + Math.pow((centroid.getVal2() - p.getVal2()), 2) + Math.pow((centroid.getVal3() - p.getVal3()), 2) + 
        Math.pow((centroid.getVal4() - p.getVal4()), 2) + Math.pow((centroid.getVal5() - p.getVal5()), 2) + Math.pow((centroid.getVal6() - p.getVal6()), 2) +
        Math.pow((centroid.getVal7() - p.getVal7()), 2) + Math.pow((centroid.getVal8() - p.getVal8()), 2) + Math.pow((centroid.getVal9() - p.getVal9()), 2) + 
        Math.pow((centroid.getVal10() - p.getVal10()), 2) + Math.pow((centroid.getVal11() - p.getVal11()), 2) + Math.pow((centroid.getVal12() - p.getVal12()), 2) +
        Math.pow((centroid.getVal13() - p.getVal13()), 2) + Math.pow((centroid.getVal14() - p.getVal14()), 2) + Math.pow((centroid.getVal15() - p.getVal15()), 2) + 
        Math.pow((centroid.getVal16() - p.getVal16()), 2) + Math.pow((centroid.getVal17() - p.getVal17()), 2) + Math.pow((centroid.getVal18() - p.getVal18()), 2) +
        Math.pow((centroid.getVal19() - p.getVal19()), 2) + Math.pow((centroid.getVal20() - p.getVal20()), 2) + Math.pow((centroid.getVal21() - p.getVal21()), 2) + 
        Math.pow((centroid.getVal22() - p.getVal22()), 2) + Math.pow((centroid.getVal23() - p.getVal23()), 2) + Math.pow((centroid.getVal24() - p.getVal24()), 2) +
        Math.pow((centroid.getVal25() - p.getVal25()), 2) + Math.pow((centroid.getVal26() - p.getVal26()), 2) + Math.pow((centroid.getVal27() - p.getVal27()), 2) +
        Math.pow((centroid.getVal28() - p.getVal28()), 2) + Math.pow((centroid.getVal29() - p.getVal29()), 2) + Math.pow((centroid.getVal30() - p.getVal30()), 2) + 
        Math.pow((centroid.getVal31() - p.getVal31()), 2) + Math.pow((centroid.getVal32() - p.getVal32()), 2) + Math.pow((centroid.getVal33() - p.getVal33()), 2) +
        Math.pow((centroid.getVal34() - p.getVal34()), 2) + Math.pow((centroid.getVal35() - p.getVal35()), 2) + Math.pow((centroid.getVal36() - p.getVal36()), 2) + 
        Math.pow((centroid.getVal37() - p.getVal37()), 2) + Math.pow((centroid.getVal38() - p.getVal38()), 2) + Math.pow((centroid.getVal39() - p.getVal39()), 2) +
        Math.pow((centroid.getVal40() - p.getVal40()), 2) + Math.pow((centroid.getVal41() - p.getVal41()), 2) + Math.pow((centroid.getVal42() - p.getVal42()), 2) + 
        Math.pow((centroid.getVal43() - p.getVal43()), 2) + Math.pow((centroid.getVal44() - p.getVal44()), 2) + Math.pow((centroid.getVal45() - p.getVal45()), 2) +
        Math.pow((centroid.getVal46() - p.getVal46()), 2) + Math.pow((centroid.getVal47() - p.getVal47()), 2) + Math.pow((centroid.getVal48() - p.getVal48()), 2) + 
        Math.pow((centroid.getVal49() - p.getVal49()), 2) + Math.pow((centroid.getVal50() - p.getVal50()), 2) + Math.pow((centroid.getVal51() - p.getVal51()), 2) +
        Math.pow((centroid.getVal52() - p.getVal52()), 2) + Math.pow((centroid.getVal53() - p.getVal53()), 2) + Math.pow((centroid.getVal54() - p.getVal54()), 2) + 
        Math.pow((centroid.getVal55() - p.getVal55()), 2) + Math.pow((centroid.getVal56() - p.getVal56()), 2) + Math.pow((centroid.getVal57() - p.getVal57()), 2) +
        Math.pow((centroid.getVal58() - p.getVal58()), 2) + Math.pow((centroid.getVal59() - p.getVal59()), 2) + Math.pow((centroid.getVal60() - p.getVal60()), 2) );
    }

    public String toString() {
    	return "("+val1+","+val2+","+ val3+"," + val4+","+val5+","+val6+","+val7+","+val8+","+val9+","+ val10+","+ 
        val11+","+val12+","+val13+","+val14+","+val15+","+val16+","+val17+","+val18+","+val19+","+val20+","+ 
        val21+","+val22+","+val23+","+val24+","+val25+","+val26+","+val27+","+val28+","+val29+","+val30+","+ 
        val31+","+val32+","+val33+","+val34+","+val35+","+val36+","+val37+","+val38+","+val39+","+val40+","+ 
        val41+","+val42+","+val43+","+val44+","+val45+","+val46+","+val47+","+val48+","+val49+","+val50+","+
        val51+","+val52+","+val53+","+val54+","+val55+","+val56+","+val57+","+val58+","+val59+","+val60+")";
    }
}
