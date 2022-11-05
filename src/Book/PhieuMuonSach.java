package Book;

public class PhieuMuonSach {
    private KhachHang kh;
    private NhanVien nv;
    private Sach sach;
    private String ngayMuon;
    private String ngayTra;

    public PhieuMuonSach() {
    }

    public PhieuMuonSach(KhachHang kh, NhanVien nv, Sach sach, String ngayMuon, String ngayTra) {
        this.kh = kh;
        this.nv = nv;
        this.sach = sach;
        this.ngayMuon = ngayMuon;
        this.ngayTra = ngayTra;
    }

    public KhachHang getKh() {
        return kh;
    }

    public void setKh(KhachHang kh) {
        this.kh = kh;
    }

    public NhanVien getNv() {
        return nv;
    }

    public void setNv(NhanVien nv) {
        this.nv = nv;
    }

    public Sach getSach() {
        return sach;
    }

    public void setSach(Sach sach) {
        this.sach = sach;
    }

    public String getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(String ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public String getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(String ngayTra) {
        this.ngayTra = ngayTra;
    }
}
