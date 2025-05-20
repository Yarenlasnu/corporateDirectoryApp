# 📞 Kurumsal Telefon Rehberi Web Uygulaması

> **Spring Boot tabanlı, kurumsal düzeyde geliştirilmiş, çok katmanlı mimariye sahip bir telefon rehberi projesi.**  
> Fakülte → Bölüm → Personel hiyerarşisiyle bilgileri yönetin, sorgulayın, filtreleyin ve görüntüleyin.  
> Geliştirici: **Hacer Yaren Ünsal** | Düzce Üniversitesi - Bilgisayar Mühendisliği

---

## 🚀 Proje Hakkında

Kurumsal yapılar için geliştirilmiş olan bu web uygulaması, kullanıcıların ve yöneticilerin, sistemdeki fakülte, bölüm ve personel verilerine hızlı ve düzenli şekilde ulaşmasını sağlar. Yönetici panelinden yetkili kişiler verileri düzenleyebilirken, kullanıcı paneli tüm ziyaretçilere açık olacak şekilde bilgi görüntüleme hizmeti sunar.

---

## 🧠 Kullanım Senaryosu

| Kullanıcı Tipi | Özellikler |
|----------------|------------|
| 👨‍💼 **Yönetici**  | Giriş yaparak fakülte, bölüm ve personel ekleyebilir/silebilir. Sadece admin erişimine özel alan. |
| 👩‍🎓 **Kullanıcı** | Sistemdeki tüm fakülte, bölüm ve personele ait bilgileri filtreleyerek görüntüleyebilir. |

---

## 🛠️ Kullanılan Teknolojiler

| Katman          | Teknoloji                                       |
|------------------|-------------------------------------------------|
| Backend          | Spring Boot, Spring MVC, Hibernate (JPA)       |
| Frontend         | JSP, JSTL, HTML5, CSS3                         |
| Veritabanı       | MySQL (Docker üzerinden)                       |
| Test Framework   | JUnit 5, Mockito                                |
| Loglama          | SLF4J, Log4j                                    |
| Uluslararasılaştırma | Spring `LocaleResolver` ile TR/EN destek       |
| Diğer            | Maven, Docker, GitHub                           |

---

## ⚙️ Proje Yapısı

kurumsal-telefon-rehberi/
├── src/
│ ├── controller/
│ ├── service/
│ ├── repository/
│ ├── entity/
│ ├── dto/
│ ├── config/
│ ├── interceptor/
│ └── test/
├── resources/
│ ├── static/
│ ├── templates/
│ └── application.properties
└── webapp/
└── WEB-INF/
└── views/
├── admin/
└── user/


---

## 🧪 Test Kapsamı

| Controller Sınıfı     | Test Sayısı | Açıklama                                 |
|------------------------|-------------|------------------------------------------|
| AdminController        | 11          | Giriş, listeleme, ekleme, silme          |
| UserController         | 6           | Fakülte, bölüm ve personel sorgulamaları |
| FakulteController      | 4           | Fakülte REST API testleri                |
| BolumController        | 4           | Bölüm REST API testleri                  |
| PersonelController     | 5           | Personel REST API testleri               |
| LanguageController     | 2           | TR/EN dil geçişi testleri                |

✅ Her controller’a ait unit testler yazıldı.  
✅ DTO tabanlı veri transferi sağlandı.  
✅ Session kontrolleri ve login güvenliği test edildi.  

---

## 🐳 Docker Kurulumu

1. **MySQL servisini başlatın:**

docker run --name rehber-mysql \
-e MYSQL_ROOT_PASSWORD=1234 \
-e MYSQL_DATABASE=telefonrehberi \
-p 3306:3306 -d mysql:latest

application properties yapılandırması :

spring.datasource.url=jdbc:mysql://localhost:3306/telefonrehberi
spring.datasource.username=root
spring.datasource.password=1234
spring.jpa.hibernate.ddl-auto=update

Uygulamayı çalıştır:
mvn spring-boot:run

Uygulama görüntüleri :
![Ekran görüntüsü 2025-05-20 045746](https://github.com/user-attachments/assets/8394764d-4872-4f59-8287-48705fcb27af)
![şifre](https://github.com/user-attachments/assets/c4170b26-4bed-4517-af57-b61608826c36)
![k panle](https://github.com/user-attachments/assets/647bbb95-9ae7-4ed7-be25-b76fc96ffb9b)
![admin 1](https://github.com/user-attachments/assets/bd653291-f25a-4ca1-963a-2882ba8123a4)



