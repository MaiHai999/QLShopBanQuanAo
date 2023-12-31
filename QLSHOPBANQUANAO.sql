USE [QuanLyBanDoTheThao]
GO
/****** Object:  Table [dbo].[ChucDanh]    Script Date: 18/05/2023 10:33:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChucDanh](
	[MaCD] [nchar](10) NOT NULL,
	[TenChucDanh] [nchar](30) NULL,
 CONSTRAINT [PK_ChucDanh] PRIMARY KEY CLUSTERED 
(
	[MaCD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CTHDBan]    Script Date: 18/05/2023 10:33:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CTHDBan](
	[MaHDB] [nchar](10) NOT NULL,
	[MaSP] [nchar](10) NOT NULL,
	[MaKH] [nchar](10) NULL,
	[SoLuong] [int] NULL,
 CONSTRAINT [PK_CTHDBan] PRIMARY KEY CLUSTERED 
(
	[MaHDB] ASC,
	[MaSP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CTHDNHap]    Script Date: 18/05/2023 10:33:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CTHDNHap](
	[MaHDN] [nchar](10) NOT NULL,
	[MaSP] [nchar](10) NOT NULL,
	[SoLuong] [int] NOT NULL,
	[GiaCungCap] [money] NOT NULL,
 CONSTRAINT [PK_CTHDNHap_1] PRIMARY KEY CLUSTERED 
(
	[MaHDN] ASC,
	[MaSP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CTKhuyenMai]    Script Date: 18/05/2023 10:33:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CTKhuyenMai](
	[MaKM] [nchar](10) NOT NULL,
	[MaSP] [nchar](10) NOT NULL,
	[PhanTramKM] [float] NULL,
 CONSTRAINT [PK_CTKhuyenMai] PRIMARY KEY CLUSTERED 
(
	[MaKM] ASC,
	[MaSP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CTPhieuDat]    Script Date: 18/05/2023 10:33:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CTPhieuDat](
	[MaSP] [nchar](10) NOT NULL,
	[MaPDH] [nchar](10) NOT NULL,
	[SoLuong] [int] NULL,
 CONSTRAINT [PK_CTPhieuDat] PRIMARY KEY CLUSTERED 
(
	[MaSP] ASC,
	[MaPDH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CTPhieuDoi]    Script Date: 18/05/2023 10:33:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CTPhieuDoi](
	[MaHDB] [nchar](10) NOT NULL,
	[MaSP] [nchar](10) NOT NULL,
	[MaPD] [nchar](10) NOT NULL,
	[SoLuong] [int] NULL,
 CONSTRAINT [PK_CTPhieuDoi] PRIMARY KEY CLUSTERED 
(
	[MaHDB] ASC,
	[MaSP] ASC,
	[MaPD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HoaDonBan]    Script Date: 18/05/2023 10:33:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDonBan](
	[MaHDB] [nchar](10) NOT NULL,
	[NgayLap] [datetime] NULL,
	[TongTien] [money] NULL,
	[MaNV] [nchar](10) NULL,
	[MaKM] [nchar](10) NULL,
 CONSTRAINT [PK_HoaDonBan] PRIMARY KEY CLUSTERED 
(
	[MaHDB] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HoaDonNhap]    Script Date: 18/05/2023 10:33:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDonNhap](
	[MaHDN] [nchar](10) NOT NULL,
	[NgayLap] [datetime] NULL,
	[TongTien] [money] NULL,
	[MaNV] [nchar](10) NULL,
	[MaPDH] [nchar](10) NULL,
	[MaNCC] [nchar](10) NULL,
 CONSTRAINT [PK_HoaDonNhap] PRIMARY KEY CLUSTERED 
(
	[MaHDN] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KhachHang]    Script Date: 18/05/2023 10:33:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhachHang](
	[MaKH] [nchar](10) NOT NULL,
	[Ten] [nchar](30) NULL,
	[GioiTinh] [nchar](10) NULL,
	[TongChiTieu] [money] NULL,
	[SDT] [nchar](12) NULL,
	[TrangThai] [nchar](20) NULL,
 CONSTRAINT [PK_KhachHang] PRIMARY KEY CLUSTERED 
(
	[MaKH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KhuyenMai]    Script Date: 18/05/2023 10:33:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhuyenMai](
	[MaKM] [nchar](10) NOT NULL,
	[TenKM] [nchar](50) NULL,
	[PhanTramKM] [float] NULL,
	[DieuKienKM] [money] NULL,
	[NgayBatDau] [datetime] NULL,
	[NgayKetThuc] [datetime] NULL,
	[LoaiKhuyenMai] [nchar](10) NULL,
 CONSTRAINT [PK_KhuyenMai] PRIMARY KEY CLUSTERED 
(
	[MaKM] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhaCungCap]    Script Date: 18/05/2023 10:33:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhaCungCap](
	[MaNCC] [nchar](10) NOT NULL,
	[TenNCC] [nchar](30) NULL,
 CONSTRAINT [PK_NhaCungCap] PRIMARY KEY CLUSTERED 
(
	[MaNCC] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 18/05/2023 10:33:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[MaNV] [nchar](10) NOT NULL,
	[Ho] [nchar](30) NULL,
	[Ten] [nchar](30) NULL,
	[GioiTinh] [nchar](10) NULL,
	[NgaySinh] [datetime] NULL,
	[Email] [nchar](70) NULL,
	[MaCD] [nchar](10) NULL,
	[MaTK] [nchar](10) NULL,
	[CMND] [nchar](20) NULL,
	[SDT] [nchar](15) NULL,
	[TrangThai] [nchar](20) NULL,
 CONSTRAINT [PK_NhanVien] PRIMARY KEY CLUSTERED 
(
	[MaNV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PhieuDatHang]    Script Date: 18/05/2023 10:33:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhieuDatHang](
	[MaPDH] [nchar](10) NOT NULL,
	[NgayDat] [date] NULL,
	[MaNV] [nchar](10) NULL,
	[MaHDN] [nchar](10) NULL,
	[NgayGiao] [date] NULL,
 CONSTRAINT [PK_PhieuDatHang] PRIMARY KEY CLUSTERED 
(
	[MaPDH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PhieuDoi]    Script Date: 18/05/2023 10:33:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhieuDoi](
	[MaPD] [nchar](10) NOT NULL,
	[NgayDoi] [nchar](10) NULL,
	[MaNV] [nchar](10) NULL,
 CONSTRAINT [PK_PhieuDoi] PRIMARY KEY CLUSTERED 
(
	[MaPD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SanPham]    Script Date: 18/05/2023 10:33:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SanPham](
	[MaSP] [nchar](10) NOT NULL,
	[TenSP] [nchar](30) NULL,
	[GiaBan] [money] NULL,
	[SoLuongSPCon] [int] NULL,
	[Size] [nchar](15) NULL,
	[Hang] [nchar](50) NULL,
	[TrangThai] [nchar](20) NULL,
 CONSTRAINT [PK_SanPham] PRIMARY KEY CLUSTERED 
(
	[MaSP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TaiKhoan]    Script Date: 18/05/2023 10:33:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TaiKhoan](
	[MaTK] [nchar](10) NOT NULL,
	[Login] [nchar](30) NULL,
	[PassWord] [nchar](500) NULL,
	[MaNV] [nchar](10) NULL,
	[TrangThai] [nchar](10) NULL,
 CONSTRAINT [PK_TaiKhoan] PRIMARY KEY CLUSTERED 
(
	[MaTK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[ChucDanh] ([MaCD], [TenChucDanh]) VALUES (N'cd01      ', N'quản lý                       ')
INSERT [dbo].[ChucDanh] ([MaCD], [TenChucDanh]) VALUES (N'cd02      ', N'Nhân viên bán hàng            ')
INSERT [dbo].[ChucDanh] ([MaCD], [TenChucDanh]) VALUES (N'cd03      ', N'Nhân viên kế toán             ')
GO
INSERT [dbo].[CTHDBan] ([MaHDB], [MaSP], [MaKH], [SoLuong]) VALUES (N'hd1       ', N'sp02      ', NULL, 3)
INSERT [dbo].[CTHDBan] ([MaHDB], [MaSP], [MaKH], [SoLuong]) VALUES (N'hd1       ', N'sp7       ', NULL, 3)
INSERT [dbo].[CTHDBan] ([MaHDB], [MaSP], [MaKH], [SoLuong]) VALUES (N'hd10      ', N'sp02      ', NULL, 3)
INSERT [dbo].[CTHDBan] ([MaHDB], [MaSP], [MaKH], [SoLuong]) VALUES (N'hd11      ', N'sp02      ', N'kh01      ', 3)
INSERT [dbo].[CTHDBan] ([MaHDB], [MaSP], [MaKH], [SoLuong]) VALUES (N'hd12      ', N'sp02      ', NULL, 2)
INSERT [dbo].[CTHDBan] ([MaHDB], [MaSP], [MaKH], [SoLuong]) VALUES (N'hd12      ', N'sp07      ', NULL, 2)
INSERT [dbo].[CTHDBan] ([MaHDB], [MaSP], [MaKH], [SoLuong]) VALUES (N'hd12      ', N'sp10      ', NULL, 5)
INSERT [dbo].[CTHDBan] ([MaHDB], [MaSP], [MaKH], [SoLuong]) VALUES (N'hd2       ', N'sp02      ', NULL, 3)
INSERT [dbo].[CTHDBan] ([MaHDB], [MaSP], [MaKH], [SoLuong]) VALUES (N'hd3       ', N'sp02      ', NULL, 2)
INSERT [dbo].[CTHDBan] ([MaHDB], [MaSP], [MaKH], [SoLuong]) VALUES (N'hd3       ', N'sp07      ', NULL, 5)
INSERT [dbo].[CTHDBan] ([MaHDB], [MaSP], [MaKH], [SoLuong]) VALUES (N'hd4       ', N'sp02      ', N'kh01      ', 5)
INSERT [dbo].[CTHDBan] ([MaHDB], [MaSP], [MaKH], [SoLuong]) VALUES (N'hd5       ', N'sp02      ', NULL, 5)
INSERT [dbo].[CTHDBan] ([MaHDB], [MaSP], [MaKH], [SoLuong]) VALUES (N'hd6       ', N'sp02      ', NULL, 3)
INSERT [dbo].[CTHDBan] ([MaHDB], [MaSP], [MaKH], [SoLuong]) VALUES (N'hd7       ', N'sp02      ', NULL, 2)
INSERT [dbo].[CTHDBan] ([MaHDB], [MaSP], [MaKH], [SoLuong]) VALUES (N'hd8       ', N'sp02      ', NULL, 3)
INSERT [dbo].[CTHDBan] ([MaHDB], [MaSP], [MaKH], [SoLuong]) VALUES (N'hd8       ', N'sp07      ', NULL, 2)
INSERT [dbo].[CTHDBan] ([MaHDB], [MaSP], [MaKH], [SoLuong]) VALUES (N'hd9       ', N'sp02      ', NULL, 3)
INSERT [dbo].[CTHDBan] ([MaHDB], [MaSP], [MaKH], [SoLuong]) VALUES (N'hd9       ', N'sp07      ', NULL, 2)
GO
INSERT [dbo].[CTHDNHap] ([MaHDN], [MaSP], [SoLuong], [GiaCungCap]) VALUES (N'hdn1      ', N'sp02      ', 4, 100000.0000)
INSERT [dbo].[CTHDNHap] ([MaHDN], [MaSP], [SoLuong], [GiaCungCap]) VALUES (N'hdn1      ', N'sp10      ', 5, 130000.0000)
INSERT [dbo].[CTHDNHap] ([MaHDN], [MaSP], [SoLuong], [GiaCungCap]) VALUES (N'hdn2      ', N'sp02      ', 5, 100000.0000)
INSERT [dbo].[CTHDNHap] ([MaHDN], [MaSP], [SoLuong], [GiaCungCap]) VALUES (N'hdn3      ', N'sp02      ', 3, 150000.0000)
INSERT [dbo].[CTHDNHap] ([MaHDN], [MaSP], [SoLuong], [GiaCungCap]) VALUES (N'hdn4      ', N'sp02      ', 3, 100000.0000)
INSERT [dbo].[CTHDNHap] ([MaHDN], [MaSP], [SoLuong], [GiaCungCap]) VALUES (N'hdn5      ', N'sp02      ', 5, 100000.0000)
GO
INSERT [dbo].[CTKhuyenMai] ([MaKM], [MaSP], [PhanTramKM]) VALUES (N'km4       ', N'sp02      ', 21000)
INSERT [dbo].[CTKhuyenMai] ([MaKM], [MaSP], [PhanTramKM]) VALUES (N'km6       ', N'sp02      ', 21000)
INSERT [dbo].[CTKhuyenMai] ([MaKM], [MaSP], [PhanTramKM]) VALUES (N'km6       ', N'sp07      ', 20000)
GO
INSERT [dbo].[HoaDonBan] ([MaHDB], [NgayLap], [TongTien], [MaNV], [MaKM]) VALUES (N'hd1       ', CAST(N'2023-02-24T00:00:00.000' AS DateTime), 210000.0000, N'nv01      ', NULL)
INSERT [dbo].[HoaDonBan] ([MaHDB], [NgayLap], [TongTien], [MaNV], [MaKM]) VALUES (N'hd10      ', CAST(N'2023-03-09T00:00:00.000' AS DateTime), 75000.0000, N'nv01      ', NULL)
INSERT [dbo].[HoaDonBan] ([MaHDB], [NgayLap], [TongTien], [MaNV], [MaKM]) VALUES (N'hd11      ', CAST(N'2023-03-11T00:00:00.000' AS DateTime), 63000.0000, N'nv01      ', NULL)
INSERT [dbo].[HoaDonBan] ([MaHDB], [NgayLap], [TongTien], [MaNV], [MaKM]) VALUES (N'hd12      ', CAST(N'2023-03-12T00:00:00.000' AS DateTime), 1332000.0000, N'nv01      ', NULL)
INSERT [dbo].[HoaDonBan] ([MaHDB], [NgayLap], [TongTien], [MaNV], [MaKM]) VALUES (N'hd2       ', CAST(N'2023-03-04T00:00:00.000' AS DateTime), 645000.0000, N'nv01      ', NULL)
INSERT [dbo].[HoaDonBan] ([MaHDB], [NgayLap], [TongTien], [MaNV], [MaKM]) VALUES (N'hd3       ', CAST(N'2023-03-04T00:00:00.000' AS DateTime), 600000.0000, N'nv01      ', NULL)
INSERT [dbo].[HoaDonBan] ([MaHDB], [NgayLap], [TongTien], [MaNV], [MaKM]) VALUES (N'hd4       ', CAST(N'2023-03-04T00:00:00.000' AS DateTime), 250000.0000, N'nv01      ', NULL)
INSERT [dbo].[HoaDonBan] ([MaHDB], [NgayLap], [TongTien], [MaNV], [MaKM]) VALUES (N'hd5       ', CAST(N'2023-03-04T00:00:00.000' AS DateTime), 250000.0000, N'nv01      ', NULL)
INSERT [dbo].[HoaDonBan] ([MaHDB], [NgayLap], [TongTien], [MaNV], [MaKM]) VALUES (N'hd6       ', CAST(N'2023-03-06T00:00:00.000' AS DateTime), 150000.0000, N'nv01      ', NULL)
INSERT [dbo].[HoaDonBan] ([MaHDB], [NgayLap], [TongTien], [MaNV], [MaKM]) VALUES (N'hd7       ', CAST(N'2023-03-06T00:00:00.000' AS DateTime), 100000.0000, N'nv01      ', NULL)
INSERT [dbo].[HoaDonBan] ([MaHDB], [NgayLap], [TongTien], [MaNV], [MaKM]) VALUES (N'hd8       ', CAST(N'2023-03-09T00:00:00.000' AS DateTime), 115000.0000, N'nv01      ', NULL)
INSERT [dbo].[HoaDonBan] ([MaHDB], [NgayLap], [TongTien], [MaNV], [MaKM]) VALUES (N'hd9       ', CAST(N'2023-03-09T00:00:00.000' AS DateTime), 115000.0000, N'nv01      ', NULL)
GO
INSERT [dbo].[HoaDonNhap] ([MaHDN], [NgayLap], [TongTien], [MaNV], [MaPDH], [MaNCC]) VALUES (N'hdn1      ', CAST(N'2023-02-24T00:00:00.000' AS DateTime), 1050000.0000, N'nv01      ', NULL, N'ncc01     ')
INSERT [dbo].[HoaDonNhap] ([MaHDN], [NgayLap], [TongTien], [MaNV], [MaPDH], [MaNCC]) VALUES (N'hdn2      ', CAST(N'2023-03-04T00:00:00.000' AS DateTime), 500000.0000, N'nv01      ', NULL, N'ncc01     ')
INSERT [dbo].[HoaDonNhap] ([MaHDN], [NgayLap], [TongTien], [MaNV], [MaPDH], [MaNCC]) VALUES (N'hdn3      ', CAST(N'2023-03-04T00:00:00.000' AS DateTime), 450000.0000, N'nv01      ', NULL, N'ncc01     ')
INSERT [dbo].[HoaDonNhap] ([MaHDN], [NgayLap], [TongTien], [MaNV], [MaPDH], [MaNCC]) VALUES (N'hdn4      ', CAST(N'2023-03-06T00:00:00.000' AS DateTime), 300000.0000, N'nv01      ', NULL, N'ncc01     ')
INSERT [dbo].[HoaDonNhap] ([MaHDN], [NgayLap], [TongTien], [MaNV], [MaPDH], [MaNCC]) VALUES (N'hdn5      ', CAST(N'2023-03-12T00:00:00.000' AS DateTime), 500000.0000, N'nv01      ', NULL, N'ncc01     ')
GO
INSERT [dbo].[KhachHang] ([MaKH], [Ten], [GioiTinh], [TongChiTieu], [SDT], [TrangThai]) VALUES (N'kh01      ', N'Mai Hai                       ', N'Nam       ', 2313000.0000, N'0396510025  ', NULL)
INSERT [dbo].[KhachHang] ([MaKH], [Ten], [GioiTinh], [TongChiTieu], [SDT], [TrangThai]) VALUES (N'kh02      ', N'Ngoc                          ', N'Nu        ', 100000.0000, N'0396510026  ', N'yes                 ')
INSERT [dbo].[KhachHang] ([MaKH], [Ten], [GioiTinh], [TongChiTieu], [SDT], [TrangThai]) VALUES (N'kh03      ', N'Vy                            ', N'Nu        ', 200000.0000, N'0396510027  ', N'no                  ')
INSERT [dbo].[KhachHang] ([MaKH], [Ten], [GioiTinh], [TongChiTieu], [SDT], [TrangThai]) VALUES (N'kh3       ', N'Hạnh                          ', N'Nam       ', 10000.0000, N'0396541555  ', N'yes                 ')
INSERT [dbo].[KhachHang] ([MaKH], [Ten], [GioiTinh], [TongChiTieu], [SDT], [TrangThai]) VALUES (N'kh4       ', N'Nguyễn Văn                    ', N'A         ', 20000.0000, N'0099887766  ', N'yes                 ')
GO
INSERT [dbo].[KhuyenMai] ([MaKM], [TenKM], [PhanTramKM], [DieuKienKM], [NgayBatDau], [NgayKetThuc], [LoaiKhuyenMai]) VALUES (N'km1       ', N'quốc khách                                        ', 30, 200000.0000, CAST(N'2023-02-11T00:00:00.000' AS DateTime), CAST(N'2023-07-30T00:00:00.000' AS DateTime), N'KM2HD     ')
INSERT [dbo].[KhuyenMai] ([MaKM], [TenKM], [PhanTramKM], [DieuKienKM], [NgayBatDau], [NgayKetThuc], [LoaiKhuyenMai]) VALUES (N'km2       ', N'lễ 2/9                                            ', 20, 500000.0000, CAST(N'2023-02-10T00:00:00.000' AS DateTime), CAST(N'2023-09-11T00:00:00.000' AS DateTime), N'KM2HD     ')
INSERT [dbo].[KhuyenMai] ([MaKM], [TenKM], [PhanTramKM], [DieuKienKM], [NgayBatDau], [NgayKetThuc], [LoaiKhuyenMai]) VALUES (N'km3       ', N'tất niên                                          ', 25, 700000.0000, CAST(N'2022-02-15T00:00:00.000' AS DateTime), CAST(N'2022-09-21T00:00:00.000' AS DateTime), N'KM2HD     ')
INSERT [dbo].[KhuyenMai] ([MaKM], [TenKM], [PhanTramKM], [DieuKienKM], [NgayBatDau], [NgayKetThuc], [LoaiKhuyenMai]) VALUES (N'km4       ', N'khai trương556                                    ', NULL, NULL, CAST(N'2021-05-22T00:00:00.000' AS DateTime), CAST(N'2022-03-27T00:00:00.000' AS DateTime), N'KM2SP     ')
INSERT [dbo].[KhuyenMai] ([MaKM], [TenKM], [PhanTramKM], [DieuKienKM], [NgayBatDau], [NgayKetThuc], [LoaiKhuyenMai]) VALUES (N'km5       ', N'quốc khách1                                       ', 25, 700000.0000, CAST(N'2023-03-11T00:00:00.000' AS DateTime), CAST(N'2023-07-30T00:00:00.000' AS DateTime), N'KM2HD     ')
INSERT [dbo].[KhuyenMai] ([MaKM], [TenKM], [PhanTramKM], [DieuKienKM], [NgayBatDau], [NgayKetThuc], [LoaiKhuyenMai]) VALUES (N'km6       ', N'khai trương555                                    ', NULL, NULL, CAST(N'2023-01-22T00:00:00.000' AS DateTime), CAST(N'2023-03-27T00:00:00.000' AS DateTime), N'KM2SP     ')
INSERT [dbo].[KhuyenMai] ([MaKM], [TenKM], [PhanTramKM], [DieuKienKM], [NgayBatDau], [NgayKetThuc], [LoaiKhuyenMai]) VALUES (N'km7       ', N'quốc khách 1                                      ', 25, 700000.0000, CAST(N'2023-03-11T00:00:00.000' AS DateTime), CAST(N'2023-09-30T00:00:00.000' AS DateTime), N'KM2HD     ')
GO
INSERT [dbo].[NhaCungCap] ([MaNCC], [TenNCC]) VALUES (N'ncc01     ', N'Công ty Phước Duy             ')
INSERT [dbo].[NhaCungCap] ([MaNCC], [TenNCC]) VALUES (N'ncc02     ', N'Công ty Hạnh Bảo              ')
INSERT [dbo].[NhaCungCap] ([MaNCC], [TenNCC]) VALUES (N'ncc03     ', N'Công ty Phước Hằng            ')
INSERT [dbo].[NhaCungCap] ([MaNCC], [TenNCC]) VALUES (N'ncc04     ', N'Kho Giao Bảo                  ')
INSERT [dbo].[NhaCungCap] ([MaNCC], [TenNCC]) VALUES (N'ncc05     ', N'Công ty Chính Lưu             ')
GO
INSERT [dbo].[NhanVien] ([MaNV], [Ho], [Ten], [GioiTinh], [NgaySinh], [Email], [MaCD], [MaTK], [CMND], [SDT], [TrangThai]) VALUES (N'nv01      ', N'Mai                           ', N'Mai Hải                       ', N'Nam       ', CAST(N'2001-12-02T00:00:00.000' AS DateTime), N'maihai@gmail.com                                                      ', N'cd01      ', N'tk01      ', N'261569885           ', N'0396510025     ', N'yes                 ')
INSERT [dbo].[NhanVien] ([MaNV], [Ho], [Ten], [GioiTinh], [NgaySinh], [Email], [MaCD], [MaTK], [CMND], [SDT], [TrangThai]) VALUES (N'nv02      ', N'Do                            ', N'Soat                          ', N'Nam       ', CAST(N'2001-05-22T00:00:00.000' AS DateTime), N'dothi@gmail.com                                                       ', N'cd02      ', N'tk02      ', N'261559996           ', N'0394512368     ', N'yes                 ')
INSERT [dbo].[NhanVien] ([MaNV], [Ho], [Ten], [GioiTinh], [NgaySinh], [Email], [MaCD], [MaTK], [CMND], [SDT], [TrangThai]) VALUES (N'nv03      ', N'Thi                           ', N'Hang                          ', N'Nu        ', CAST(N'2000-12-25T00:00:00.000' AS DateTime), N'hammt@gmail.com                                                       ', N'cd03      ', N'tk03      ', N'123456789           ', N'0891234567     ', N'yes                 ')
INSERT [dbo].[NhanVien] ([MaNV], [Ho], [Ten], [GioiTinh], [NgaySinh], [Email], [MaCD], [MaTK], [CMND], [SDT], [TrangThai]) VALUES (N'nv4       ', N'Hong                          ', N'Hạnh                          ', N'Nu        ', CAST(N'2001-02-05T00:00:00.000' AS DateTime), N'maihonghanh1995@gmail.com                                             ', N'cd01      ', N'tk4       ', N'12345678            ', N'03965410029    ', N'no                  ')
INSERT [dbo].[NhanVien] ([MaNV], [Ho], [Ten], [GioiTinh], [NgaySinh], [Email], [MaCD], [MaTK], [CMND], [SDT], [TrangThai]) VALUES (N'nv5       ', N'Hong                          ', N'Hạnh                          ', N'Nu        ', CAST(N'2000-12-25T00:00:00.000' AS DateTime), N'maihonghanh1995@gmail.com                                             ', N'cd02      ', N'tk5       ', N'999999              ', N'03965410029    ', N'no                  ')
GO
INSERT [dbo].[SanPham] ([MaSP], [TenSP], [GiaBan], [SoLuongSPCon], [Size], [Hang], [TrangThai]) VALUES (N'sp02      ', N'Áo thun thể thao              ', 21000.0000, 204, N'x              ', N'luxary                                            ', NULL)
INSERT [dbo].[SanPham] ([MaSP], [TenSP], [GiaBan], [SoLuongSPCon], [Size], [Hang], [TrangThai]) VALUES (N'sp07      ', N'Áo bơi                        ', 20000.0000, 158, N'xx             ', N'lux                                               ', NULL)
INSERT [dbo].[SanPham] ([MaSP], [TenSP], [GiaBan], [SoLuongSPCon], [Size], [Hang], [TrangThai]) VALUES (N'sp10      ', N'Quần dài thể thao             ', 250000.0000, 65, N's              ', N'way                                               ', NULL)
INSERT [dbo].[SanPham] ([MaSP], [TenSP], [GiaBan], [SoLuongSPCon], [Size], [Hang], [TrangThai]) VALUES (N'sp7       ', N'Quần thun                     ', 30000.0000, 25, N'm              ', N'ways                                              ', NULL)
GO
INSERT [dbo].[TaiKhoan] ([MaTK], [Login], [PassWord], [MaNV], [TrangThai]) VALUES (N'tk01      ', N'haimai999                     ', N'$2a$10$Qnirvm5/SM.JszuTwwMxgu5fWo71gwf/uaDz3Mb9HidtCZU38uS0u                                                                                                                                                                                                                                                                                                                                                                                                                                                        ', N'nv01      ', N'active    ')
INSERT [dbo].[TaiKhoan] ([MaTK], [Login], [PassWord], [MaNV], [TrangThai]) VALUES (N'tk02      ', N'okconde1                      ', N'$2a$10$SevPnjLJOTvoi6r79EcRvOIgYWdDhM3m5YrrUt9fqdCayGi6AZHZu                                                                                                                                                                                                                                                                                                                                                                                                                                                        ', N'nv02      ', N'active    ')
INSERT [dbo].[TaiKhoan] ([MaTK], [Login], [PassWord], [MaNV], [TrangThai]) VALUES (N'tk03      ', N'sala                          ', N'$2a$10$vC9ebzSlZDW87oKP/b6dgOZiEMh5Yw36A044.2KmImm9NQFUlG7p6                                                                                                                                                                                                                                                                                                                                                                                                                                                        ', N'nv03      ', N'active    ')
INSERT [dbo].[TaiKhoan] ([MaTK], [Login], [PassWord], [MaNV], [TrangThai]) VALUES (N'tk4       ', N'ongbataolo                    ', N'                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    ', N'nv4       ', N'inactive  ')
INSERT [dbo].[TaiKhoan] ([MaTK], [Login], [PassWord], [MaNV], [TrangThai]) VALUES (N'tk5       ', NULL, NULL, N'nv5       ', N'inactive  ')
GO
ALTER TABLE [dbo].[CTHDBan]  WITH CHECK ADD  CONSTRAINT [FK_CTHDBan_HoaDonBan] FOREIGN KEY([MaHDB])
REFERENCES [dbo].[HoaDonBan] ([MaHDB])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[CTHDBan] CHECK CONSTRAINT [FK_CTHDBan_HoaDonBan]
GO
ALTER TABLE [dbo].[CTHDBan]  WITH CHECK ADD  CONSTRAINT [FK_CTHDBan_KhachHang] FOREIGN KEY([MaKH])
REFERENCES [dbo].[KhachHang] ([MaKH])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[CTHDBan] CHECK CONSTRAINT [FK_CTHDBan_KhachHang]
GO
ALTER TABLE [dbo].[CTHDBan]  WITH CHECK ADD  CONSTRAINT [FK_CTHDBan_SanPham] FOREIGN KEY([MaSP])
REFERENCES [dbo].[SanPham] ([MaSP])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[CTHDBan] CHECK CONSTRAINT [FK_CTHDBan_SanPham]
GO
ALTER TABLE [dbo].[CTHDNHap]  WITH CHECK ADD  CONSTRAINT [FK_CTHDNHap_HoaDonNhap] FOREIGN KEY([MaHDN])
REFERENCES [dbo].[HoaDonNhap] ([MaHDN])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[CTHDNHap] CHECK CONSTRAINT [FK_CTHDNHap_HoaDonNhap]
GO
ALTER TABLE [dbo].[CTHDNHap]  WITH CHECK ADD  CONSTRAINT [FK_CTHDNHap_SanPham] FOREIGN KEY([MaSP])
REFERENCES [dbo].[SanPham] ([MaSP])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[CTHDNHap] CHECK CONSTRAINT [FK_CTHDNHap_SanPham]
GO
ALTER TABLE [dbo].[CTKhuyenMai]  WITH CHECK ADD  CONSTRAINT [FK_CTKhuyenMai_KhuyenMai1] FOREIGN KEY([MaKM])
REFERENCES [dbo].[KhuyenMai] ([MaKM])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[CTKhuyenMai] CHECK CONSTRAINT [FK_CTKhuyenMai_KhuyenMai1]
GO
ALTER TABLE [dbo].[CTKhuyenMai]  WITH CHECK ADD  CONSTRAINT [FK_CTKhuyenMai_SanPham1] FOREIGN KEY([MaSP])
REFERENCES [dbo].[SanPham] ([MaSP])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[CTKhuyenMai] CHECK CONSTRAINT [FK_CTKhuyenMai_SanPham1]
GO
ALTER TABLE [dbo].[CTPhieuDat]  WITH CHECK ADD  CONSTRAINT [FK_CTPhieuDat_PhieuDatHang] FOREIGN KEY([MaPDH])
REFERENCES [dbo].[PhieuDatHang] ([MaPDH])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[CTPhieuDat] CHECK CONSTRAINT [FK_CTPhieuDat_PhieuDatHang]
GO
ALTER TABLE [dbo].[CTPhieuDat]  WITH CHECK ADD  CONSTRAINT [FK_CTPhieuDat_SanPham] FOREIGN KEY([MaSP])
REFERENCES [dbo].[SanPham] ([MaSP])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[CTPhieuDat] CHECK CONSTRAINT [FK_CTPhieuDat_SanPham]
GO
ALTER TABLE [dbo].[CTPhieuDoi]  WITH CHECK ADD  CONSTRAINT [FK_CTPhieuDoi_CTHDBan] FOREIGN KEY([MaHDB], [MaSP])
REFERENCES [dbo].[CTHDBan] ([MaHDB], [MaSP])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[CTPhieuDoi] CHECK CONSTRAINT [FK_CTPhieuDoi_CTHDBan]
GO
ALTER TABLE [dbo].[CTPhieuDoi]  WITH CHECK ADD  CONSTRAINT [FK_CTPhieuDoi_PhieuDoi] FOREIGN KEY([MaPD])
REFERENCES [dbo].[PhieuDoi] ([MaPD])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[CTPhieuDoi] CHECK CONSTRAINT [FK_CTPhieuDoi_PhieuDoi]
GO
ALTER TABLE [dbo].[HoaDonBan]  WITH CHECK ADD  CONSTRAINT [FK_HoaDonBan_KhuyenMai1] FOREIGN KEY([MaKM])
REFERENCES [dbo].[KhuyenMai] ([MaKM])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[HoaDonBan] CHECK CONSTRAINT [FK_HoaDonBan_KhuyenMai1]
GO
ALTER TABLE [dbo].[HoaDonBan]  WITH CHECK ADD  CONSTRAINT [FK_HoaDonBan_NhanVien] FOREIGN KEY([MaNV])
REFERENCES [dbo].[NhanVien] ([MaNV])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[HoaDonBan] CHECK CONSTRAINT [FK_HoaDonBan_NhanVien]
GO
ALTER TABLE [dbo].[HoaDonNhap]  WITH CHECK ADD  CONSTRAINT [FK_HoaDonNhap_NhaCungCap] FOREIGN KEY([MaNCC])
REFERENCES [dbo].[NhaCungCap] ([MaNCC])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[HoaDonNhap] CHECK CONSTRAINT [FK_HoaDonNhap_NhaCungCap]
GO
ALTER TABLE [dbo].[HoaDonNhap]  WITH CHECK ADD  CONSTRAINT [FK_HoaDonNhap_NhanVien1] FOREIGN KEY([MaNV])
REFERENCES [dbo].[NhanVien] ([MaNV])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[HoaDonNhap] CHECK CONSTRAINT [FK_HoaDonNhap_NhanVien1]
GO
ALTER TABLE [dbo].[NhanVien]  WITH CHECK ADD  CONSTRAINT [FK_NhanVien_ChucDanh] FOREIGN KEY([MaCD])
REFERENCES [dbo].[ChucDanh] ([MaCD])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[NhanVien] CHECK CONSTRAINT [FK_NhanVien_ChucDanh]
GO
ALTER TABLE [dbo].[PhieuDatHang]  WITH CHECK ADD  CONSTRAINT [FK_PhieuDatHang_HoaDonNhap] FOREIGN KEY([MaHDN])
REFERENCES [dbo].[HoaDonNhap] ([MaHDN])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[PhieuDatHang] CHECK CONSTRAINT [FK_PhieuDatHang_HoaDonNhap]
GO
ALTER TABLE [dbo].[PhieuDatHang]  WITH CHECK ADD  CONSTRAINT [FK_PhieuDatHang_NhanVien] FOREIGN KEY([MaNV])
REFERENCES [dbo].[NhanVien] ([MaNV])
GO
ALTER TABLE [dbo].[PhieuDatHang] CHECK CONSTRAINT [FK_PhieuDatHang_NhanVien]
GO
ALTER TABLE [dbo].[PhieuDoi]  WITH CHECK ADD  CONSTRAINT [FK_PhieuDoi_NhanVien] FOREIGN KEY([MaNV])
REFERENCES [dbo].[NhanVien] ([MaNV])
GO
ALTER TABLE [dbo].[PhieuDoi] CHECK CONSTRAINT [FK_PhieuDoi_NhanVien]
GO
ALTER TABLE [dbo].[TaiKhoan]  WITH CHECK ADD  CONSTRAINT [FK_TaiKhoan_NhanVien] FOREIGN KEY([MaNV])
REFERENCES [dbo].[NhanVien] ([MaNV])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[TaiKhoan] CHECK CONSTRAINT [FK_TaiKhoan_NhanVien]
GO
