# ShopEase - E-Commerce Backend

**ShopEase** es una plataforma de comercio electrónico diseñada para facilitar la compra y venta de productos online. Este repositorio contiene el backend de la aplicación, construido utilizando **Java** con **Spring Boot** y **Spring Data JPA**, y está estructurado en microservicios.

## Descripción

El sistema permite a los usuarios crear cuentas, gestionar su carrito de compras, realizar pedidos y administrarlos. Los administradores y empleados tienen acceso a funcionalidades adicionales para gestionar productos, categorías y pedidos.

### Funcionalidades Principales

- **Usuarios**: Creación de cuentas para usuarios, empleados y administradores.
- **Carrito de Compras**: Los usuarios pueden agregar productos al carrito y generar un pedido.
- **Productos**: Los administradores pueden agregar, modificar y eliminar productos.
- **Categorías**: Los productos están organizados en categorías para facilitar la navegación.
- **Pedidos**: Los usuarios pueden realizar pedidos, que son gestionados por los administradores.

### Microservicios

El sistema está dividido en varios microservicios, cada uno encargado de una funcionalidad específica:

1. **User Service**: Maneja la creación y gestión de usuarios.
2. **Product Service**: Administra los productos y sus detalles.
3. **Cart Service**: Permite a los usuarios agregar productos a su carrito de compras.
4. **Order Service**: Gestiona la creación de pedidos y sus detalles.

## Requisitos

- **Java 17+**
- **Spring Boot**
- **Spring Data JPA**
- **MySQL** (Base de datos)
- **Maven** (para gestionar dependencias y construir el proyecto)

## Instalación

### 1. Clonar el repositorio

```bash
git clone https://github.com/tu_usuario/ShopEase.git
cd ShopEase
