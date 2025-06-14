# HealthTuring - Aplicación Web Sanitaria

![Banner](/img/banner.jpg)

HealthTuring es una aplicación web diseñada para gestionar información médica de pacientes, programar citas y facilitar la comunicación entre profesionales de la salud.

---

## 📑 Índice

1. [Autores del Proyecto](#-autores-del-proyecto)  
2. [Descripción General](#-descripción-general)  
3. [Objetivos del Proyecto](#-objetivos-del-proyecto)  
4. [Tecnologías Utilizadas](#-tecnologías-utilizadas)  
5. [Diseño y Recursos Visuales](#-diseño-y-recursos-visuales)
6. [Ejemplos de la Aplicación](#-ejemplos-de-la-aplicación)  
7. [Enlaces Importantes](#-enlaces-importantes)  
8. [Documentación Técnica](#-documentación-técnica)  
9. [Licencia](#-licencia)


---

## 👥 Autores del Proyecto

- Juan Francisco Chacón Macías  
- Álvaro López Guerrero

---

## 📌 Descripción General

HealthTuring es una aplicación web desarrollada como trabajo de fin de curso del ciclo formativo en Desarrollo de Aplicaciones Web. Está orientada a facilitar la gestión de tratamientos médicos, reservas de citas, historial clínico y la comunicación directa entre pacientes y doctores mediante un sistema de chat en línea.

---

## 🎯 Objetivos del Proyecto

- Desarrollar una plataforma web **segura y escalable** para la gestión sanitaria.  
- Implementar un sistema de **autenticación robusto** que proteja la información sensible de los pacientes.  
- **Mejorar la comunicación** entre pacientes y médicos.  
- **Optimizar el seguimiento** de tratamientos médicos.  
- Aplicar conocimientos adquiridos sobre **desarrollo web full-stack**.  
- Fomentar el **trabajo colaborativo** utilizando buenas prácticas de desarrollo.

---

## 🛠️ Tecnologías Utilizadas

- **Frontend:** Angular, Tailwind CSS, CSS3 (Landing Page)  
- **Backend:** SpringBoot con Hibernate, Spring Data JPA  
- **Base de Datos:** MySQL  
- **Herramientas y Librerías:** Git, GitHub, Figma, JWT, WebSocket  
- **Despliegue:** AWS, Nginx

---

## 🎨 Diseño y Recursos Visuales

- 📄 **Esquema E/R de la Base de Datos**
  
  ![Esquema E/R](/img/ER.HealthTuring.jpg)

- 🎨 **Diseño en Figma:**

  [Ver Diseño en Figma](https://www.figma.com/design/CmWyjN19e1JvGNwSS9at7q/Proyecto-dise%C3%B1o?node-id=13-3&t=FCEvTD1tZ9ZOurUy-1)

---

## 💻 Ejemplos de la aplicación

- 🔐 **Autenticación y autorización**

  Inicio de sesión con autenticación mediante **JWT** (token firmado), y protección de rutas y endpoints basada en el rol del usuario (USER, ADMIN, DOC), utilizando la configuración de seguridad de **Spring Security** y **Angular Guards** para proteger las rutas en el frontend. Todas las peticiones REST son interceptadas mediante un interceptor, que agrega automáticamente el token en las cabeceras para validar la autenticación y autorizar según el rol correspondiente.

  Registro con envío de un correo electrónico que contiene un token seguro para confirmar la cuenta antes de poder iniciar sesión, en el caso de los usuarios tipo paciente. Si el registro se realiza como médico, deberá esperar a que el equipo de administración apruebe la solicitud.
  
  Funcionalidad para cambiar la contraseña tras solicitar el restablecimiento, accediendo mediante un enlace enviado por correo electrónico que incluye un token firmado de un solo uso, con una validez de 30 minutos.
  
  Todos los formularios de la aplicación utilizan **ReactiveFormsModule** de Angular, junto con **Validators** para las validaciones de campos.
  
  Tanto en caso de éxito como de error en cada operación, se muestran notificaciones tipo toast al usuario mediante el paquete **ngx-toastr**, para proporcionar retroalimentación clara sobre el estado de las acciones realizadas.
  
  ![Auth](/img/auth.png)

- 📆 **Calendario**
 
  Calendario adaptado con angular-calendar, configurado para mostrar los eventos por meses (con navegación entre ellos), incluyendo los tratamientos asignados a los pacientes y las citas pendientes con su médico, pudiendo cambiar en todo momento entre los perfiles de pacientes que tiene el usuario. También se utiliza en la vista del médico para visualizar sus citas programadas con los pacientes.

  ![Calendar](/img/calendar.jpg)

- 💬 **Chat Online**

  Chat en línea, seguro y privado entre pacientes y sus médicos, implementado mediante WebSocket. Con almacenamiento persistente de los mensajes intercambiados. Además, el acceso al chat está protegido según el rol y la relación entre paciente y médico, asegurando que solo los usuarios autorizados puedan comunicarse entre sí.

  ![Chat](/img/chat.jpg)

- 📋 **Gestión de tratamientos**

  Como médico, puedes gestionar los tratamientos de tus pacientes desde una vista en formato tabla (adaptada a cards en dispositivos móviles), con paginación implementada gracias al uso de **JpaRepository de Spring Data JPA**. Desde esta interfaz, es posible añadir nuevos tratamientos, así como editar o eliminar los ya existentes de forma sencilla y eficiente.

  ![GestiónTratamientos](/img/treatments.jpg)

- 💊 **Datos paciente y reserva de citas**

  Como paciente, podrás acceder a todos tus datos personales y médicos. También tendrás la posibilidad de consultar tus tratamientos, incluyendo su duración, dosis, fechas y demás detalles relevantes, así como las incompatibilidades entre los medicamentos recetados.

  Además, podrás reservar citas con tu médico asignado seleccionando un día disponible. Al hacerlo, se habilitará un campo para elegir entre los horarios libres (slots) de ese doctor. Una vez confirmada la cita, recibirás un correo electrónico con todos los detalles para su confirmación.

  ![DatosCitas](/img/data-booking.png)

- 👮 **Vista Administrador**

  Como usuario administrador, accedes al panel de administración desarrollado con vistas de **Thymeleaf en Spring Boot MVC**, donde puedes gestionar los medicamentos, incompatibilidades y sustancias de la aplicación, con la posibilidad de añadir, editar o eliminar registros.

  Además, desde este panel se administra la validación de nuevos usuarios registrados con el rol de médico, pudiendo aceptar o rechazar sus solicitudes. También permite asignar médicos con disponibilidad a los nuevos perfiles de pacientes, cuando un usuario crea dicho perfil a través del formulario modal para ello.

  ![Admin](/img/admin.png)

---

## 🔗 Enlaces Importantes

- 🌐 **Landing Page:**  
  [https://healthturing.duckdns.org](https://healthturing.duckdns.org)

- 📹 **Vídeo del Proyecto:**  
  [Ver en YouTube](https://youtu.be/oIjWwXb3p8M)

- 🎥 **Vídeo de CheckPoint:**  
  [Ver en YouTube](https://youtu.be/Zk5RYo0uuNk)

- 📋 **Bitácora de Tareas (Notion):**  
  [Acceder a la bitácora](https://messy-muskox-c6b.notion.site/1e553eb02c1e80b99aecd3bc1395805c?v=1e553eb02c1e80009cd4000cb875567c)

- 📄 **Presentación en PDF:**  
  [Ver presentación](https://drive.google.com/file/d/ejemplo/view)

- 🧪 **Anteproyecto (Notion):**  
  [Ver Anteproyecto](https://messy-muskox-c6b.notion.site/Anteproyecto-1c053eb02c1e800b8074d49e43f14007)

- 📑 **Documentación de la API:**  
  [Ver API.md](./API.md)

---

## 📚 Documentación Técnica

### Backend

- [Spring Framework](https://docs.spring.io/spring-framework/reference/index.html)  
- [Spring Email Sender (JavaMailSender)](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/mail/javamail/JavaMailSender.html)  
- [Spring Data JPA](https://docs.spring.io/spring-data/jpa/docs/current-SNAPSHOT/reference/html/#reference)  
- [Hibernate](https://hibernate.org/orm/documentation/6.6/)  
- [MySQL](https://dev.mysql.com/doc/)  
- [JWT (JSON Web Tokens)](https://jwt.io/)  
- [Docker](https://docs.docker.com/)

### Frontend

- [Angular](https://angular.dev/overview)  
- [ngx-toastr (Toast Notifications)](https://www.npmjs.com/package/ngx-toastr)  
- [Tailwind CSS](https://v2.tailwindcss.com/docs)  
- [Material Icons](https://fonts.google.com/icons)  
- [angular-calendar](https://www.npmjs.com/package/angular-calendar)  
- [Chart.js](https://www.chartjs.org/)  
- [jwt-decode](https://www.npmjs.com/package/jwt-decode)

---

## 📄 Licencia

© 2025 - HealthTuring. Proyecto educativo desarrollado por alumnos del CFGS Desarrollo de Aplicaciones Web.
