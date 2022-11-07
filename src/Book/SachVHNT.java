package Book;

public class SachVHNT extends Sach{
    private static int slTungLoai;
    private int slTungLoai1;

    private static String loai;

    private String loai1;

    static  {
        loai = "Văn Học Nghệ Thuật";
    }
    public SachVHNT() {

    }

    public SachVHNT(String maSach, String tenSach, String tenTacGia, int nxb, int soLuongMoiCuon, int doTuoi, boolean duocMuon, int soSachMuon) {
        super(maSach, tenSach, tenTacGia, nxb, soLuongMoiCuon, doTuoi, duocMuon, soSachMuon);
    }

    public int getSlTungLoai() {
        return slTungLoai;
    }

    public void setSlTungLoai(int slTungLoai) {
        SachVHNT.slTungLoai = slTungLoai;
    }

    public int getSlTungLoai1() {
        return slTungLoai1;
    }

    public void setSlTungLoai1(int slTungLoai1) {
        this.slTungLoai1 = slTungLoai1;
    }
    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }
    public String getLoai1() {
        return loai1;
    }

    public void setLoai1(String loai1) {
        this.loai1 = loai1;
    }
}
