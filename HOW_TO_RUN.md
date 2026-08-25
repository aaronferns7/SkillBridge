# SkillBridge — How to Run

This repository contains two applications:

| Component | Directory | Purpose |
| --- | --- | --- |
| Android client | `app/` | Jetpack Compose student freelancing app |
| REST API | `backend/` | Spring Boot 3 API with an in-memory H2 database |

## Prerequisites

Install the following before starting:

- Android Studio (latest stable), including an Android SDK platform matching `compileSdk 37`.
- JDK 17. Set `JAVA_HOME` to the JDK 17 installation.
- Maven 3.9+ for the Spring Boot backend (`mvn -version` should work).
- An Android emulator with API 26+ or a physical Android device.
- Internet access on the first build so Gradle and Maven can download dependencies.

Verify the Java and Maven installation in PowerShell:

```powershell
java -version
mvn -version
```

`java -version` must report Java 17. Android Studio may use its bundled JDK for Gradle, but the backend still needs JDK 17 available to Maven.

## 1. Get the code

```powershell
git clone <YOUR_REPOSITORY_URL> skillbridge
cd skillbridge
```

If you already have the repository, update it with your normal team workflow, for example `git pull` on the intended branch.

## 2. Start the backend

Open a terminal at the repository root:

```powershell
cd backend
mvn spring-boot:run
```

The API starts at `http://localhost:8080`. It seeds three users and ten jobs each time it starts. Because H2 is in-memory, all data resets when the server stops.

Useful checks:

```powershell
# In a second terminal
Invoke-RestMethod http://localhost:8080/api/jobs
Invoke-RestMethod "http://localhost:8080/api/jobs?category=Coding&maxPay=60"
```

The H2 console is available while the backend is running at `http://localhost:8080/h2-console`.

Use this JDBC URL in the console:

```text
jdbc:h2:mem:skillbridge
```

## 3. Run the Android app

1. Open the repository root (`C:\skillbridge`) in Android Studio.
2. Wait for Gradle sync to finish. Accept SDK/component installation prompts if Android Studio shows them.
3. Start an emulator, then choose the `app` run configuration and press Run.

Or use PowerShell from the repository root:

```powershell
$env:GRADLE_USER_HOME = "$PWD\.gradle-user"
.\gradlew.bat :app:assembleDebug
```

The generated debug APK will be under `app\build\outputs\apk\debug\`.

## Android-to-backend networking

The Retrofit base URL is currently configured in `AppModule.kt` as:

```kotlin
http://10.0.2.2:8080/
```

That is correct for the standard Android Studio emulator: `10.0.2.2` maps from the emulator to your development computer's `localhost`.

For a physical phone, replace `10.0.2.2` with the computer's LAN IPv4 address, for example:

```kotlin
http://192.168.1.42:8080/
```

The phone and computer must be on the same network, and Windows Firewall must permit inbound traffic on port 8080. Restart the app after changing the address.

## API reference

All responses use this envelope:

```json
{ "success": true, "data": {}, "message": "ok" }
```

| Method | Route | Description |
| --- | --- | --- |
| GET | `/api/jobs` | Job list; supports `category`, `maxDistance`, `minPay`, `maxPay`, `isRemote` |
| GET | `/api/jobs/{id}` | Job detail |
| GET | `/api/jobs/nearby?lat=&lng=&radius=` | Nearby jobs |
| GET | `/api/users/{id}` | User profile |
| GET | `/api/users/{id}/portfolio` | User portfolio |
| GET | `/api/users/{id}/reviews` | User reviews |
| POST | `/api/users/register` | Register a user |
| POST | `/api/auth/login` | Returns a development mock JWT |
| POST | `/api/applications` | Submit an application |
| GET | `/api/applications?studentId=` | Student applications |

## Troubleshooting

**Gradle dependency download fails**

Check internet access, proxy/VPN settings, then retry Gradle sync. The first Android build downloads the Gradle distribution and dependencies.

**`mvn` is not recognized**

Install Maven 3.9+, then reopen PowerShell so its `bin` directory is on `PATH`.

**The Android app shows no internet / cannot load jobs**

Confirm the backend terminal is still running and visit `http://localhost:8080/api/jobs` in the host browser. For the emulator use `10.0.2.2`; for a physical device use the computer's LAN IP as described above.

**Android SDK/compile SDK error**

Use Android Studio SDK Manager to install Android API 37, then sync the project again.

## Production note

The included API uses H2 and a mock login token for development only. Before deployment, replace H2 with a persistent database, configure environment-based API URLs and secrets, and implement real authentication/token validation.
