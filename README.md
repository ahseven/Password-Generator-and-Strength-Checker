

# 🛡️ Passworder

**Passworder** is a robust, lightweight password strength validator and generator built in Java. It is designed for users who prioritize privacy, running entirely **locally and safely** on your own machine without ever sending data to the cloud.

---

## ✨ Features

* **Strength Validation:** Analyzes passwords for complexity (length, digits, symbols, and casing).
* **Breach Check:** Scans your input against the famous `rockyou.txt` wordlist (14+ million known leaked passwords) using memory-efficient **File Streaming**.
* **Secure Generation:** Instantly creates high-entropy passwords tailored to your needs.
* **ASCII UI:** Clean terminal interface with custom banners for a professional feel.

---

## 🚀 Getting Started

### 1. Prerequisites
* **Java JDK 17 or higher** installed.
* The `rockyou.txt` dataset.

### 2. Installation & Setup

1. **Clone the repository:**
   ```bash
   git clone [https://github.com/your-username/passworder.git](https://github.com/your-username/passworder.git)
   cd passworder

```

2. **Retrieve the Wordlist:**
Due to its size (155MB+), `rockyou.txt` is not included in the repository.
* **Download:** [Link to rockyou.txt zip here]
* **Action:** Extract the `.zip` and place the `rockyou.txt` file directly into the project root folder.



### 3. File System Structure

To ensure the program runs correctly, your directory should look exactly like this:

```text
.
├── banner.txt       # ASCII Art Branding
├── passworder.java  # Main Application Logic
└── rockyou.txt      # Common Password Database (extracted)

```

---

## 🛠️ Usage

Once you have retrieved `rockyou.txt` and placed it in the folder, launch the application by running:

```bash
java passworder.java

```

---

## 🧠 Technical Implementation

| Concept | Description |
| --- | --- |
| **File Streaming** | Processes 14+ million passwords using `java.util.stream`. It compares lines directly from the disk to maintain a tiny RAM footprint. |
| **Short-Circuiting** | The search logic stops immediately upon finding a match, ensuring maximum efficiency. |
| **Regex Validation** | Utilizes advanced Regular Expressions to score entropy accurately without complex loops. |

---

## 📝 Important Notes

> [!IMPORTANT]
> **Safety First:** This program runs locally. Your passwords are never transmitted over the internet, making it significantly safer than web-based "password strength" tools.

> [!TIP]
> If the program fails to find the wordlist, ensure the file is named exactly `rockyou.txt`. On Windows, double-check that you haven't accidentally named it `rockyou.txt.txt`.

---

© 2024 Passworder Project

```

Would you like me to create a `.gitignore` file for you as well, so you don't accidentally try to upload that massive 155MB `rockyou.txt` to GitHub?

```
