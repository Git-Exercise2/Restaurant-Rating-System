# 🍽️ Restaurant Rating System

A full-stack restaurant review platform designed to deliver a clean, intuitive experience for diners and powerful content management tools for administrators.

This system includes:
- A native Android app for end users
- A responsive web-based admin dashboard
- A secure, scalable backend built with Spring Boot and MySQL

---

## 📲 Mobile Application (Android)

Developed with native Java in Android Studio, the mobile app provides restaurant seekers with a simple and user-friendly interface.

### 🌟 Features
- 🔐 User registration and login
- 🍴 Browse nearby restaurants with real-time search
- ⭐ Post ratings and textual reviews
- 💬 Reply to comments
- 🚩 Report inappropriate content
- ❤️ Add restaurants to favorites

> Data is dynamically loaded using JSON from the backend, with support for smooth image rendering (Glide) and secure request handling.

---

## 🖥️ Admin Dashboard (Vue.js)

The web-based admin panel, built using Vue 3 + Element Plus, enables administrators to manage platform content and moderate user interactions.

### 🛠️ Capabilities
- 📋 Manage restaurant listings (manual or via Yelp API)
- 👁️ View and moderate user reviews and reports
- ❌ Handle reports by deleting content or banning users
- 📤 Export data to Excel
- 🛡️ Role-based access (admin only)

---

## ⚙️ Backend API (Spring Boot + MyBatis)

The backend provides a RESTful API, ensuring high-performance communication between frontends and the MySQL database.

### 🔐 Core Features
- ✅ JWT-based token authentication
- 🔐 BCrypt password hashing
- 🛡️ CSRF protection
- 🎯 DTO-based API responses
- 🔗 Yelp Fusion API integration with deduplication logic

---

## 🧰 Tech Stack Overview

| Layer         | Technology                             |
|---------------|-----------------------------------------|
| Mobile App    | Android (Java), Retrofit, Glide         |
| Web Panel     | Vue 3, Element Plus, Axios              |
| Backend       | Spring Boot, MyBatis, JWT, CSRF, BCrypt |
| Database      | MySQL                                   |
| Integration   | [Yelp Fusion API](https://www.yelp.com/developers) |