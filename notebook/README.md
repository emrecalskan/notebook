Personal Notebook Application

Bu proje, kişisel notları saklamak ve yönetmek için basit bir masaüstü uygulaması sunar.
 Kullanıcılar not ekleyebilir, güncelleyebilir, silebilir ve mevcut notlarını görüntüleyebilirler.
  Uygulama, JavaFX kullanılarak geliştirilmiş olup SQLite veritabanı ile veri saklamaktadır.

Özellikler

-Not Ekleme: Kullanıcılar yeni notlar ekleyebilir.
-Not Güncelleme: Kullanıcılar mevcut notlarını güncelleyebilir.
-Not Silme: Kullanıcılar istedikleri notları silebilir.
-Not Listeleme: Uygulama, tüm notları listeleyerek kullanıcıya görüntüler.

Kullanılan Teknolojiler

JavaFX: Kullanıcı arayüzü (UI) oluşturmak için kullanıldı.
SQLite: Veritabanı olarak SQLite kullanıldı. Veriler bu veritabanında saklanır.
JDBC: Veritabanı ile bağlantı kurmak için JDBC kullanıldı.
Java: Uygulama Java programlama diliyle yazıldı.

Proje Yapısı

main/ : Uygulamanın ana arayüzünü ve işlemleri yöneten sınıflar burada yer alır.
db/ : Veritabanı bağlantısı ve işlemlerini yöneten sınıflar burada bulunur.
models/ : Notları ve veritabanı işlemlerini modelleyen sınıflar bu klasörde yer alır.
observer/ : Observer deseni ile not değişikliklerini izleyen sınıflar bulunur.
state/ : Notların durumlarını yöneten sınıflar burada bulunur.
views/ : Kullanıcı arayüzü bileşenlerini içeren sınıflar burada yer alır.

Kullanım

1. Bağımlılıklar: Projeyi çalıştırmadan önce, Java ve JavaFX kurulumlarının yapılmış olması gerekmektedir.
2. Veritabanı: SQLite veritabanı bağlantısı otomatik olarak yapılacaktır. Uygulama, notebook.db
adlı veritabanı dosyasını kullanır.
3. Çalıştırma: Uygulamayı çalıştırmak için App.java dosyasını çalıştırabilirsiniz. 
Bu, JavaFX uygulamanızın başlatılmasını sağlar.

Proje Tasarımı

Bu projede çeşitli yazılım tasarım desenleri kullanılmıştır. Tasarım desenleri, yazılımın daha esnek,
bakımı kolay ve sürdürülebilir olmasını sağlamak için uygulanır. Bu projede kullanılan başlıca 
tasarım desenleri:

1.Singleton Design Pattern 
Projedeki Kullanımı: Database sınıfı, veritabanı bağlantısının yalnızca bir kez yapılmasını ve
bu bağlantının farklı sınıflar arasında paylaşılmasını sağlar. Veritabanı bağlantısı yalnızca bir kez
başlatılır ve uygulama boyunca aynı bağlantı kullanılmaya devam eder. Bu, veritabanı bağlantılarının 
verimli bir şekilde yönetilmesini sağlar.

2.Factory Design Pattern
Projedeki Kullanımı: NoteFactory sınıfı, Note nesnelerini oluşturmak için kullanılır. Bu sınıf, 
not nesnelerinin çeşitli şekillerde (ID, başlık ve içerik ile veya yalnızca içerik ile) yaratılmasını 
sağlar. Böylece not nesnelerinin yaratılması merkezi bir yerden yönetilir.

3.DAO (Data Access Object) Design Pattern
Projedeki Kullanımı: NoteDao sınıfı, not verilerini işlemek için kullanılan bir DAO sınıfıdır. 
Bu sınıf, not ekleme, güncelleme, silme ve listeleme işlemlerini gerçekleştirir. Veritabanı erişimi, 
NoteDao sınıfının içinde tutulur, böylece uygulama diğer bileşenlerden (örneğin, kullanıcı arayüzü) 
bağımsız hale gelir.

4.Observer Design Pattern
Projedeki Kullanımı: NoteSubject sınıfı, notların durumunu izleyen bir gözlemci (observer) yönetici 
rolü üstlenir. NoteObserver arayüzü, notlardaki değişiklikleri gözlemlemek isteyen sınıflar tarafından 
implement edilir. Örneğin, UserObserver sınıfı, kullanıcıların not değişikliklerini almasını sağlar.

5.State Design Pattern
Projedeki Kullanımı: NoteContext sınıfı, notların durumunu yöneten bir yapıdır. Notlar, "taslak" (DraftState) 
veya "tamamlanmış" (CompletedState) gibi farklı durumlarda olabilir. Durumlar, State arayüzü ile soyutlanır 
ve her durum farklı bir davranış sergiler. Durumlar arasında geçişler NoteContext sınıfı tarafından yönetilir.

6.MVC (Model-View-Controller) Design Pattern
Projedeki Kullanımı: Bu projede NoteDao (Model) notlarla ilgili veriyi ve iş mantığını yönetirken, App ve 
MainView (View) kullanıcı arayüzünü oluşturur. Kullanıcıdan gelen etkileşimler (ekleme, silme, güncelleme) 
Controller tarafından işlenir ve uygun işlemler yapılır.



Projeye Katkıda Bulunanlar

- Teoman Yavuz 
github profili : https://github.com/teomanyavuz

- Emre Çalışkan
github profili : https://github.com/emrecalskan