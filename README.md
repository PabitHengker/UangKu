# UangKu

# 💰 UangKu - Personal Finance & Expense Tracker  **UangKu** adalah aplikasi manajemen keuangan pribadi modern berbasis Android yang dibangun menggunakan **Jetpack Compose** dan **Material 3**. Aplikasi ini dirancang untuk membantu pengguna melacak transaksi harian, mengelola multi-rekening (Bank & E-Wallet), serta menganalisis pola pengeluaran melalui visualisasi data yang interaktif.  
---  
## ✨ Fitur Utama (Key Features)  
- 📊 **Dashboard & Financial Summary**
- Pemantauan total saldo secara real-time.
- Ringkasan daftar transaksi terbaru dengan kategori interaktif (Expenses, Income, Savings).
- Quick-switch tampilan dompet/rekening utama.
- 📈 **Analytics & Data Visualization**
- Grafik Donut Chart kustom (`Canvas` + `drawArc`) untuk distribusi kategori pengeluaran.
- Card ringkasan total pemasukan vs pengeluaran.
- Detail rincian kategori dilengkapi dengan *percentage progress bar*.
- 🏦 **Wallet & Account Management**
- Integrasi status sinkronisasi multi-rekening (BCA, GoPay, OVO, Jago, dll.).
- Indikator proporsi saldo antar akun.
- ⚙️ **Security & Preferences (Profile Page)**
- Pengamanan aplikasi menggunakan **Biometric Authentication** (Fingerprint/FaceID) dan **Auto-Lock**.
- Proteksi privasi tingkat lanjut dengan `FLAG_SECURE` (mencegah *screenshot* pada area sensitif).
- Fitur ekspor data transaksi (CSV) dan opsi pengelolaan data lokal.
---
## 🛠️ Tech Stack & Architecture  
- **Language:** Kotlin
- **UI Framework:** Jetpack Compose (Material 3)
- **Minimum SDK:** API 28 (Android 9.0)
- **Target SDK:** API 36
- **Build System:** Gradle (AGP 8.13.2) & Compose BOM (2024.09.00)
- **Architecture Pattern:** Clean Architecture / Unidirectional Data Flow (UDF)
- **Design System:** Custom Design Tokens (Typography, Shapes, and Extended Color Scheme)
---
## 🚀 Getting Started  
1. Clone repositori ini:    ```bash    git clone [https://github.com/username/UangKu.git](https://github.com/username/UangKu.git)
