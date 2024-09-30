# Dhanwantari - Telemedicine App

**Dhanwantari** is an Android-based telemedicine app designed to improve healthcare delivery efficiency by enabling early detection of diseases, remote patient monitoring, online prescription management, and secure video consultations. The app integrates advanced machine learning algorithms for disease prediction, real-time health data tracking via the Google Fit API, and video conferencing through Jitsi SDK.

## Key Features

- **Disease Prediction**: Utilizes a machine learning model (Multinomial Naive Bayes) to predict diseases based on patient symptoms. The app processes user inputs (symptoms) and returns the most probable disease, along with information about treatment.
- **Remote Patient Monitoring**: Health data such as steps, calories burned, heart rate, glucose levels, and more are tracked using the **Google Fit API** and monitored remotely by doctors.
- **Telemedicine**: Real-time, secure video consultations between doctors and patients are enabled through the **Jitsi SDK** (leveraging **WebRTC** technology).
- **Online Prescription Management**: After consultations, doctors can provide patients with electronic prescriptions stored in the app and accessible for online orders.
- **Doctor Registration and Verification**: A robust system for verifying doctor credentials, ensuring only qualified professionals provide healthcare through the app.

## Technology Stack

- **Java**: Core language for Android app development.
- **Google Fit API**: Enables real-time tracking of health metrics such as steps, calories, glucose levels, and heart rate.
- **Machine Learning (Python)**: Multinomial Naive Bayes classifier for disease prediction, hosted on **Heroku**.
- **Jitsi SDK**: For video conferencing between doctors and patients.
- **Firebase**: Used for authentication, real-time data management, and notifications.
- **Volley API**: Connects the Android app with the hosted machine learning model for prediction processing.

## Machine Learning Model: Multinomial Naive Bayes

### Overview

The disease prediction model in **Dhanwantari** is based on a **Multinomial Naive Bayes** classifier, which is highly suitable for multi-class classification problems where the features (symptoms) are discrete. The model takes patient symptoms as input and outputs the most likely disease. This model uses the statistical properties of conditional probability distributions under the assumption of independence between features.

### Mathematical Explanation

The Multinomial Naive Bayes classifier applies **Bayes' theorem**:

\[
P(C_k | X) = \frac{P(C_k) \cdot P(X | C_k)}{P(X)}
\]

Where:

- \( P(C_k | X) \) is the posterior probability of class \( C_k \) (disease) given feature vector \( X \) (symptoms).
- \( P(C_k) \) is the prior probability of class \( C_k \) (the likelihood of a disease occurring in the population).
- \( P(X | C_k) \) is the likelihood of observing the feature vector \( X \) given the class \( C_k \).
- \( P(X) \) is the probability of the feature vector \( X \) (marginal likelihood).

### Naive Bayes Assumption

In Naive Bayes, it is assumed that the features (symptoms) are conditionally independent given the class (disease). Thus, the likelihood term can be factored as a product of the individual feature probabilities:

\[
P(X | C_k) = P(x_1 | C_k) \cdot P(x_2 | C_k) \cdot ... \cdot P(x_n | C_k)
\]

Where \( x_1, x_2, ..., x_n \) are the individual symptoms (features).

### Multinomial Model

The multinomial version of Naive Bayes is used for discrete features, where each feature represents the number of occurrences of a symptom. The conditional probability \( P(x_i | C_k) \) is computed based on the frequency of symptom \( x_i \) appearing in patients with disease \( C_k \).

Given a feature vector \( X = (x_1, x_2, ..., x_n) \), the probability of observing this vector given a disease \( C_k \) is:

\[
P(X | C_k) = \frac{(N_k)!}{\prod_{i=1}^{n} (x_i!)} \prod_{i=1}^{n} \left( \frac{\theta_{k, i}^{x_i}}{x_i!} \right)
\]

Where:

- \( N_k \) is the total number of symptom occurrences for class \( C_k \).
- \( \theta_{k, i} \) is the probability of feature \( x_i \) occurring in class \( C_k \).
- \( x_i \) is the count of symptom \( i \) for a patient.

### Model Training

The model is trained on a dataset of disease-symptom associations, where the training data consists of multiple symptom sets labeled with the corresponding disease. The parameters \( \theta_{k, i} \) for each class are estimated from the training data by computing the frequency of each symptom given the disease.

The model outputs the most probable disease by calculating the posterior probabilities for each possible disease and selecting the disease with the highest posterior probability.

### Deployment

The trained machine learning model is deployed on **Heroku**, and the Android app communicates with it using the **Volley API**. The app sends symptom data entered by the user to the model, which returns the predicted disease along with additional information such as treatment recommendations.

## Dataset

The machine learning model is trained on a dataset from **New York Presbyterian Hospital**, containing disease-symptom associations derived from patient discharge summaries. The dataset includes the 150 most frequent diseases and their associated symptoms, ranked by the strength of association.

## Setup and Installation

### Prerequisites

- **Android Studio**: Installed on your machine.
- **Heroku Account**: To deploy the machine learning model.
- **Google Cloud**: To access the Google Fit API.
- **Firebase**: For user authentication and real-time database management.

### Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/ansschh/Dhanwantari-Telemedicine_app.git
    cd Dhanwantari-Telemedicine_app
    ```

2. Open the project in Android Studio and sync the Gradle files.

3. Set up Firebase for authentication and real-time data storage.

4. Deploy the machine learning model on **Heroku**.

5. Enable **Google Fit API** in the **Google Cloud Console** for health data monitoring.

### Usage

1. **Disease Prediction**:
   - Users enter symptoms via the app.
   - The machine learning model predicts the disease and displays the results along with treatment information.

2. **Remote Patient Monitoring**:
   - The app tracks patient health data (e.g., steps, heart rate) using **Google Fit**.
   - Doctors can view this data in real-time to monitor patient health remotely.

3. **Video Consultations**:
   - Patients can schedule a video call with a doctor, which is conducted via the **Jitsi SDK** for secure communication.

4. **Prescription Management**:
   - After consultations, doctors can send electronic prescriptions to patients through the app. The prescriptions are saved in the app and can be used for online medicine orders.

## Screenshots

- Symptom Entry Screen
- Disease Prediction Results
- Doctor Registration and Verification
- Slot Booking and Video Consultation
- Prescription Management

## Paper and Research

For more detailed insights into the development and methodology of the app, please refer to the paper:

[Dhanwantari: An Android Application to Increase Service Delivery in Healthcare Industry](./DhanwantariFinal.pdf)

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.

## Citation

If you use **Dhanwantari** in your research or project, please cite the following paper:

_Ansh Tiwari. "Dhanwantari: An Android Application to Increase Service Delivery in Healthcare Industry" (2022). DOI: 10.14293/S2199-1006.1.SOR-.PPWFVIU.v1_


## Contact

For questions or issues, please contact:

- **Ansh Tiwari** (anshtiwari9899@gmail.com)
