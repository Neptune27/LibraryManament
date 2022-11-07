package Book;

public class PhieuMuonSach {
    private String maKhachHang;
    private String maNhanVien;
    private String maSach;
    private int soLuong;
    private NgayThangNam ngayMuon;
    private NgayThangNam ngayTra;

    public PhieuMuonSach() {
    }

    public PhieuMuonSach(String maKhachHang, String maNhanVien, String maSach, int soLuong, NgayThangNam ngayMuon, NgayThangNam ngayTra) {
        this.maKhachHang = maKhachHang;
        this.maNhanVien = maNhanVien;
        this.maSach = maSach;
        this.soLuong = soLuong;
        this.ngayMuon = ngayMuon;
        this.ngayTra = ngayTra;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public NgayThangNam getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(NgayThangNam ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public NgayThangNam getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(NgayThangNam ngayTra) {
        this.ngayTra = ngayTra;
    }

    @Override
    public String toString() {
        return  "maKhachHang='" + maKhachHang + '\'' +
                ", maNhanVien='" + maNhanVien + '\'' +
                ", maSach='" + maSach + '\'' +
                ", soLuong=" + soLuong +
                ", ngayMuon=" + ngayMuon +
                ", ngayTra=" + ngayTra;
    }
}
