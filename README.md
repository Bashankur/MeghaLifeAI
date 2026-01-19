# MeghaLifeAI 🇮🇳

MeghaLifeAI is a smart Android application designed to improve tourism safety,
transport visibility, and emergency preparedness in North-East India, with a
primary focus on Meghalaya.

The system is built to be reliable, explainable, and government-ready, with
lightweight on-device AI that works even in low-connectivity regions.

---

## 🚩 Problem Statement

Tourists and residents in Meghalaya face multiple challenges:

- Fragmented and unreliable transport information
- Safety risks due to hilly terrain and unpredictable weather
- Limited real-time visibility of public transport
- Language and accessibility barriers
- Poor network availability in remote regions

These issues impact tourism confidence, public safety, and governance efficiency.

---

## 💡 Solution Overview

MeghaLifeAI provides a single, integrated mobile platform that offers:

- Tourist exploration through an interactive map
- AI-based travel risk assessment
- Emergency and safety assistance
- Live location sharing for public transport drivers
- Multilingual support for accessibility

The application is designed to support both tourists and local residents while
being scalable for government deployment.

---

## 🧠 Primary AI Feature (Core System)

### Travel Risk Classification (On-Device AI)

The core AI component of MeghaLifeAI is a lightweight **TensorFlow Lite**
classification model that assesses travel risk for tourists.

### Inputs
- Travel month
- Weather condition (Clear / Fog / Rain)
- Terrain type (City / Hill / Waterfall)
- Road alert status

### Output
- **LOW**, **MEDIUM**, or **HIGH** travel risk

### Why this AI approach?
- Runs fully **on-device**
- Works offline or in low-network areas
- Low latency and battery-efficient
- Transparent and explainable
- No personal data is transmitted

---

## 🏗️ System Architecture (High Level)
User
↓
Android App (Jetpack Compose)
↓
On-device TensorFlow Lite Model
↓
Local Business Logic + Maps + Firebase


---

## 🛠️ Technology Stack

- Android (Jetpack Compose)
- Kotlin
- TensorFlow Lite (On-device inference)
- Firebase Authentication (Anonymous login)
- Google Maps SDK
- Material 3 Design System

---

## ▶️ Setup & Run Instructions

### Prerequisites
- Android Studio (Giraffe or later)
- Android SDK 33+
- A valid Google Maps API key

### Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/<your-username>/MeghaLifeAI.git
2. Open the project in Android Studio

2. Add your Google Maps API key to:
   local.properties

4. Sync Gradle and run the app on an emulator or physical device.


