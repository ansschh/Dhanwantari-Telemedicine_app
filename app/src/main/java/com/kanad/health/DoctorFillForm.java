package com.kanad.health;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class DoctorFillForm extends AppCompatActivity {
    TextView doctor_name,selectedText_radioButton,stateofdoctorfill,districtofdoctorfill,dummy_for_doctor_name,getdoctor_uid_dummy2;
    ArrayAdapter<String> list_of_degrees;
    ArrayAdapter<String> specilization_list;
    EditText yearofexpirirnce;
    TextView nextbutton;
    RadioGroup radiogroup;
    String Image_Url;
    RadioButton owner,rented,consultant,practicing;
    TextInputLayout specilization_inputtext,degree_inputtext,college_inputtext,yearofcompletion_inputtext,yearofexpirience_inputTextVew;
    ArrayAdapter<String> name_of_medical_college;
    ArrayAdapter<CharSequence> yearofcompletion;
    AutoCompleteTextView degree,specializations, institute, year_of_comepletion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_fill_form);
        TextView back;
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        doctor_name = findViewById(R.id.doctor_name);
        year_of_comepletion = findViewById(R.id.yearofcomepletion);
        degree = findViewById(R.id.degree);
        owner = findViewById(R.id.owner);
        nextbutton = findViewById(R.id.nextbutton);
        getdoctor_uid_dummy2 = findViewById(R.id.getdoctor_uid_dummy2);
        dummy_for_doctor_name = findViewById(R.id.dummy_for_doctor_name);
        districtofdoctorfill = findViewById(R.id.districtofdoctorfill);
        stateofdoctorfill = findViewById(R.id.stateofdoctorfill);
        yearofexpirience_inputTextVew = findViewById(R.id.yearofexpirience_inputTextVew);
        practicing = findViewById(R.id.practicing);
        selectedText_radioButton = findViewById(R.id.selectedText_radioButton);
        rented = findViewById(R.id.rented);
        consultant = findViewById(R.id.consultant);
        radiogroup = findViewById(R.id.radiogroup);
        specilization_inputtext = findViewById(R.id.specilization_inputtext);
        degree_inputtext = findViewById(R.id.degree_inputtext);
        college_inputtext = findViewById(R.id.college_inputtext);
        yearofcompletion_inputtext = findViewById(R.id.yearofcompletion_inputtext);
        yearofexpirirnce = findViewById(R.id.yearofexpirirnce);
        institute = findViewById(R.id.institute);
        specializations = findViewById(R.id.specializations);
        if (FirebaseAuth.getInstance().getCurrentUser()==null){
            StorageReference storageRef = FirebaseStorage.getInstance().getReference();
            storageRef.child("images/"+GoogleSignIn.getLastSignedInAccount(DoctorFillForm.this).getId().toString()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Image_Url = uri.toString();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    Toast.makeText(DoctorFillForm.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            StorageReference storageRef = FirebaseStorage.getInstance().getReference();
            storageRef.child("images/"+FirebaseAuth.getInstance().getCurrentUser().getUid().toString()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Image_Url = uri.toString();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    Toast.makeText(DoctorFillForm.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        String[] listofdegrees = {"Bachelor of Medicine, Bachelor of Surgery (MBBS, BMBS, MBChB, MBBCh)", "Bachelor of Medicine (B.Med)",
        "Bachelor of Surgery (B.S)/(B.Surg)",
        "Doctor of Medicine (MD, Dr.MuD, Dr.Med)",
        "Doctor of Osteopathic Medicine (DO)",
        "Doctor of Medicine by research MD(Res), DM",
        "Master of Clinical Medicine (MCM)",
        "Master of Medical Science (MMSc, MMedSc)",
        "Master of Public Health (MPH)",
        "Master of Medicine (MM, MMed)",
        "Master of Philosophy (MPhil)",
        "Master of Philosophy in Ophthalmology (MPhO)",
        "Master of Public Health and Ophthalmology (MPHO)",
        "Master of Surgery (MS, MSurg, MChir, MCh, ChM, CM)",
        "Master of Science in Medicine or Surgery (MSc)",
        "Doctor of Clinical Medicine (DCM)",
        "Doctor of Clinical Surgery (DClinSurg)",
        "Doctor of Medical Science (DMSc, DMedSc)",
        "Doctor of Surgery (DS, DSurg)",
        "Doctor of Podiatric Medicine (DPM)",
        "Ayurveda - BSc, MSc, BAMC, MD (Ayurveda), M.S. (Ayurveda), Ph.D. (Ayurveda)",
        "Siddha medicine - BSMS, MD(Siddha), Ph.D(Siddha)",
        "Acupuncture - BSc, LAc, DAc, AP, DiplAc, MAc",
        "Chiropractic -",
        "Herbalism Acs, BSc, Msc.",
        "Homeopathy - BSc, MSc, DHMs, BHMS, M.D. (HOM), PhD in homoeopathy",
        "Naprapathy - DN",
        "Naturopathic medicine - BSc, MSc, BNYS, MD (Naturopathy), ND, NMD",
        "BSc",
        "MSOM",
        "MSTOM",
        "KMD (Korea)",
        "BCM (Hong Kong)",
        "MCM (Hong Kong)",
        "BChinMed (Hong Kong)",
        "MChinMed (Hong Kong)",
        "MD (Taiwan)",
        "MB (China)",
        "TCM-Traditional Chinese medicine master (China)",
        "BOst", "BOstMed", "BSc (Osteo)", "DipOsteo"};
        String[] specialization = {"Allergy and Immunology", "Critical care medicine", "Hospice and palliative care", "Pain medicine", "Pediatric anesthesiology", "Sleep medicine", "Dermatopathology", "Pediatric dermatology", "Procedural dermatology", "Abdominal radiology", "Breast imaging", "Cardiothoracic radiology", "Cardiovascular radiology", "Chest radiology", "Emergency radiology", "Endovascular surgical neuroradiology", "Gastrointestinal radiology", "Genitourinary radiology", "Head and neck radiology", "Interventional radiology", "Musculoskeletal radiology", "Neuroradiology", "Nuclear radiology", "Pediatric radiology", "Radiation oncology", "Vascular and interventional radiology", "Anesthesiology critical care medicine", "Emergency medical services", "Hospice and palliative medicine", "Internal medicine / Critical care medicine", "Medical toxicology", "Pain medicine", "Pediatric emergency medicine", "Sports medicine", "Undersea and hyperbaric medicine", "Adolescent medicine", "Geriatric medicine", "Hospice and palliative medicine", "Pain medicine", "Sleep medicine", "Sports medicine", "Advanced heart failure and transplant cardiology", "Cardiovascular disease", "Clinical cardiac electrophysiology", "Critical care medicine", "Endocrinology, diabetes, and metabolism", "Gastroenterology", "Geriatric medicine", "Hematology", "Hematology and oncology", "Infectious disease", "Internal medicine", "Interventional cardiology", "Nephrology", "Oncology", "Pediatric internal medicine", "Pulmonary disease", "Pulmonary disease and critical care medicine", "Rheumatology", "Sleep medicine", "Sports medicine", "Transplant hepatology", "Biochemical genetics", "Clinical cytogenetics", "Clinical genetics", "Molecular genetic pathology", "Brain injury medicine", "Child neurology", "Clinical neurophysiology", "Endovascular surgical neuroradiology", "Hospice and palliative medicine", "Neurodevelopmental disabilities", "Neuromuscular medicine", "Pain medicine", "Sleep medicine", "Vascular neurology", "NUCLEAR MEDICINE", "OBSTETRICS AND GYNECOLOGY", "Female pelvic medicine and reconstructive surgery", "Gynecologic oncology", "Maternal-fetal medicine", "Reproductive endocrinologists and infertility", "Anterior segment/cornea ophthalmology", "Glaucoma ophthalmology", "Neuro-ophthalmology", "Ocular oncology", "Oculoplastics/orbit", "Ophthalmic Plastic & Reconstructive Surgery", "Retina/uveitis", "Strabismus/pediatric ophthalmology", "Anatomical pathology", "Blood banking and transfusion medicine", "Chemical pathology", "Clinical pathology", "Cytopathology", "Forensic pathology", "Genetic pathology", "Hematology", "Immunopathology", "Medical microbiology", "Molecular pathology", "Neuropathology", "Pediatric pathology", "Adolescent medicine", "Child abuse pediatrics", "Developmental-behavioral pediatrics", "Neonatal-perinatal medicine", "Pediatric cardiology", "Pediatric critical care medicine", "Pediatric endocrinology", "Pediatric gastroenterology", "Pediatric hematology-oncology", "Pediatric infectious diseases", "Pediatric nephrology", "Pediatric pulmonology", "Pediatric rheumatology", "Pediatric sports medicine", "Pediatric transplant hepatology", "Brain injury medicine", "Hospice and palliative medicine", "Neuromuscular medicine", "Pain medicine", "Pediatric rehabilitation medicine", "Spinal cord injury medicine", "Sports medicine", "Aerospace medicine", "Medical toxicology", "Occupational medicine", "Public health medicine", "Addiction psychiatry", "Administrative psychiatry", "Child and adolescent psychiatry", "Community psychiatry", "Consultation/liaison psychiatry", "Emergency psychiatry", "Forensic psychiatry", "Geriatric psychiatry", "Mental retardation psychiatry", "Military psychiatry", "Pain medicine", "Psychiatric research", "Psychosomatic medicine", "Hospice and palliative medicine", "Pain medicine", "Colon and rectal surgery", "General surgery", "Surgical critical care", "Gynecologic oncology", "Plastic surgery", "Craniofacial surgery", "Hand surgery", "Neurological surgery", "Endovascular surgical neuroradiology", "Ophthalmic surgery", "Oral and maxillofacial surgery", "Orthopedic surgery", "Adult reconstructive orthopedics", "Foot and ankle orthopedics", "Musculoskeletal oncology", "Orthopedic sports medicine", "Orthopedic surgery of the spine", "Orthopedic trauma", "Pediatric orthopedics", "Otolaryngology", "Pediatric otolaryngology", "Otology neurotology", "Pediatric surgery", "Neonatal", "Prenatal", "Trauma", "Pediatric oncology", "Surgical Intensivists, specializing in critical care patients", "Thoracic Surgery", "Congenital cardiac surgery", "Thoracic surgery-integrated", "Vascular surgery", "Pediatric urology", "Urologic oncology", "Renal transplant", "Male infertility", "Calculi", "Female urology", "Neurourology"};
        String[] medicalCollegeNames = {"Dr. NTR University of Health Sciences Vijayawada [5]", "All India Institute of Medical Sciences, Mangalagiri", "Andhra Medical College, Visakhapatnam", "Sri Venkateswara Medical College, Tirupati", "Sri Venkateswara Institute of Medical Sciences, Tirupati", "Government Medical College, Srikakulam", "Government Medical College, Anantapur", "Guntur Medical College, Guntur", "Kurnool Medical College, Kurnool", "Rajiv Gandhi Institute of Medical Sciences, Ongole", "Rajiv Gandhi Institute of Medical Sciences, Kadapa", "Rangaraya Medical College, Kakinada", "Siddhartha Medical College, Vijayawada", "A. C. Subba Reddy Government Medical College, Nellore", "Sri Sathya Sai Institute of Higher Medical Sciences, Puttaparthi", "NRI Medical College, Guntur", "Katuri Medical College, Guntur", "P.E.S. Institute of Medical Sciences and Research, Kuppam", "Apollo institute of medical sciences and research, Chittoor", "Tomo Riba Institute of Health and Medical Sciences, Naharlagun", "All India Institute of Medical Sciences, Guwahati", "Assam Medical College and Hospital (AMCH), Dibrugarh", "Fakhruddin Ali Ahmed Medical College and Hospital, Barpeta", "Gauhati Medical College and Hospital (GMCH), Guwahati", "Jorhat Medical College and Hospital (JMCH), Jorhat", "Silchar Medical College and Hospital (SMCH), Silchar", "Tezpur Medical College and Hospital (TMCH), Tezpur", "Diphu Medical College and Hospital (DMCH), Diphu", "Lakhimpur Medical College and Hospital, North Lakhimpur", "Anugrah Narayan Magadh Medical College and Hospital", "All India Institute of Medical Sciences, Patna", "Darbhanga Medical College and Hospital", "Government Medical College, Bettiah", "Indira Gandhi Institute of Medical Sciences", "Jawaharlal Nehru Medical College and Hospital", "Katihar Medical College and Hospital", "Lord Buddha Koshi Medical College and Hospital", "Patna Medical College and Hospital", "Nalanda Medical College Hospital", "Narayan Medical College and Hospital", "Rajendra Memorial Research Institute of Medical Sciences", "Sri Krishna Medical College and Hospital", "Vardhman Institute of Medical Sciences", "Post Graduate Institute of Medical Education and Research", "Government Medical College and Hospital Chandigarh", "All India Institute of Medical Sciences, Raipur", "Chhattisgarh Institute of Medical Sciences, Bilaspur", "Government Medical College, Raigarh", "Government Medical College, Rajnandgaon", "Late Baliram Kashyap Memorial Government Medical College, Jagdalpur", "Pt. Jawahar Lal Nehru Memorial Medical College, Raipur", "Raipur Institute of Medical Sciences, Raipur", "Goa Dental College, Bambolim", "Goa Medical College, Bambolim", "Shri Kamaxshi Devi Homeopathic Medical College & Hospital", "AMC MET Medical College, Maninagar", "Baroda Medical College, Vadodara", "B.J. Medical College, Ahmedabad[20]", "Government Medical College, Bhavnagar[21]", "Government Medical College, Surat[22]", "Gujarat Adani Institute of Medical Sciences, Bhuj", "M. P. Shah Medical College[23]", "Pandit Deendayal Upadhyay Medical College", "Smt. NHL Municipal Medical College, Ahmedabad", "Surat Municipal Institute of Medical Education and Research", "Bhagat Phool Singh Medical College, Sonipat, 100 seats", "ESIC Medical College, Faridabad, 125 seats", "Kalpana Chawla Government Medical College, Karnal, 100 seats", "Pandit Bhagwat Dayal Sharma Post Graduate Institute of Medical Sciences, Rohtak, 200 seats", "Shaheed Hasan Khan Mewati Government Medical College, Nalhar, Mewat, 100 seats", "Maharaja Agrasen Medical College, Agroha, Hisar, 100 seats", "N. C. Medical College and Hospital, Israna, Panipat, 150 seats.", "Maharishi Markandeshwar Institute of Medical Sciences and Research, Ambala, 100 seats", "Shree Guru Gobind Singh Tricentenary University (SGT Medical College), Bhudera, Gurgaon, 150 seats", "World College of Medical Sciences, Jhajjar", "Indira Gandhi Medical College, Shimla", "Dr. Rajendra Prasad Government Medical College Kangra", "Shri Lal Bahadur Shastri Government Medical College and Hospital, Mandi", "Government Medical College, Srinagar", "Sher-i-Kashmir Institute of Medical Sciences", "Government Medical College, Jammu", "Government Medical College, Baramulla", "Government Medical College, Anantnag", "Government Medical College, Rajouri", "Government Medical College, Kathua", "All India Institute of Medical Sciences, Deoghar", "Hazaribag Medical College", "Mahatma Gandhi Memorial Medical College, Jamshedpur", "Medini Rai Medical College and Hospital, Medininagar", "Patliputra Medical College and Hospital", "Phulo Jhano Murmu Medical College and Hospital, Dumka", "Rajendra Institute of Medical Sciences", "A J Institute of Medical Science, Mangalore", "Adichunchanagiri Institute of Medical Sciences, B G Nagara", "Al-Ameen Medical College, Bijapur", "Bangalore Medical College and Research Institute, Bangalore", "Bidar Institute of Medical Sciences, Bidar", "Basaveshwara Medical College and Hospital, Chitradurga", "East Point College of Medical Sciences and Research Center, Bangalore", "ESIC Medical College, Gulbarga", "Father Muller Medical College, Mangalore", "Gulbarga Institute of Medical Sciences, Gulbarga", "Jawaharlal Nehru Medical College, Belgaum", "JSS medical college, Mysore", "Karnataka Institute of Medical Sciences, Hubli", "Kasturba Medical College, Mangalore", "Kasturba Medical College, Manipal", "Kempegowda Institute of Medical Sciences, Bangalore", "Khaja Banda Nawaz Institute of Medical Sciences, Gulbarga", "Koppal Institute of Medical Sciences, Koppal", "KS Hegde Medical Academy Mangalore", "Mysore Medical College", "Mahadevappa Rampure Medical College, Gulbarga.", "Mandya Institute of Medical Sciences, Mandya", "National Institute of Mental Health and Neurosciences, Bangalore", "Navodaya Medical College, Raichur", "Oxford Medical College, Bangalore", "Rajarajeswari Medical College and Hospital, Bangalore", "S Nijalingappa Medical College, HSK (Hanagal Shree Kumareshwar) Hospital and Research Centre", "S. S. Institute of Medical Sciences, Davangere", "Sri Devaraj Urs Medical College, Kolar", "Shimoga Institute of Medical Sciences, Shimoga", "St John's Medical College, Bangalore", "Vijayanagara Institute of Medical Sciences", "Vydehi Institute of Medical Sciences and Research Centre", "Government Medical College, Thiruvananthapuram", "Government Medical College, Kozhikode", "Government Medical College, Kottayam", "Government T D Medical College, Alappuzha", "Government Medical College, Thrissur", "Government Medical College, Ernakulam", "Government Medical College, Malappuram", "Government Medical College, Palakkad", "Government Medical College, Kollam", "Government Medical College, Kannur", "Government Medical College, Konni", "Al Azhar Medical College, Thodupuzha, Idukki", "Amala Institute of Medical Sciences, Thrissur", "Amrita Institute of Medical Sciences and Research Centre, Kochi", "Azeezia Institute of Medical Sciences & Research, Meeyyannoor, Kollam, Kollam", "Believers Church Medical College Hospital, Pathanamthitta", "DM Wayanad Institute of Medical sciences, Wayanad", "Dr. Somervell Memorial CSI Medical College, Thiruvananthapuram", "Jubilee Mission Medical College and Research Institute, Thrissur", "Kannur Medical College, Kannur", "Karuna Medical College, Palakkad", "Kerala Medical College, Palakkad", "KMCT Medical College, Mukkam, Kozhikode", "Malabar Medical College, Kozhikode", "Malankara Orthodox Syrian Church Medical Mission Hospital, Ernakulam", "MES Academy of Medical Sciencesm Malappuram", "Mount Zion Medical College, Pathanamthitta", "PK Das Institute of Medical Sciences, Palakkad", "Pushpagiri Medical College, Thiruvalla", "Sree Gokulam Medical College & Research Foundation, Thiruvananthapuram", "Sree Narayana Institute of Medical Sciences, Ernakulam", "SUT Medical College, Thiruvananthapuram", "Travancore Medical College Hospital, Kollam", "SR Medical College Hospital, Varkala", "Hinduhridaysamrat Balasaheb Thackeray Medical College and Dr. R. N. Cooper Municipal General Hospital, Juhu, Mumbai", "King Edward Memorial Hospital and Seth Gordhandas Sunderdas Medical College, Parel, Mumbai", "Lokmanya Tilak Municipal General Hospital, Sion, Mumbai", "Topiwala National Medical College and B.Y.L. Nair Charitable Hospital, Mumbai", "All India Institute of Medical Sciences, Nagpur", "Armed Forces Medical College, Pune", "Government Medical College & General Hospital, Baramati", "Government Medical College, Chandrapur", "Government Medical College, Latur", "Government Medical College, Miraj", "Government Medical College, Nagpur", "Indira Gandhi Government Medical College, Nagpur", "Grant Medical College, Byculla, Mumbai", "Rajiv Gandhi Medical College, Kalwa, Thane", "R.C.S.M. Govt Medical College and CPR Hospital, Kolhapur", "Shri Bhausaheb Hire Government Medical College, Dhule", "Shri Vasantrao Naik Government Medical College, Yavatmal", "Swami Ramanand Teerth Rural Medical College, Ambajogai", "Government Medical College, Jalgaon", "Government Medical College, Gondia", "Mahatma Gandhi Institute of Medical Sciences, Wardha", "ACPM Medical College", "Ashwini Medical College Hospital, Solapur", "Bharati Vidyapeeth Deemed University Medical College and Hospital, Pune", "Bharati Vidyapeeth Deemed University Medical College and Hospital, Sangli", "Dr. D. Y. Patil Medical College, Hospital & Research Centre, Kohlapur", "Dr. Panjabrao Deshmukh Memorial Medical College", "K. J. Somaiya Medical College & Research Centre, Mumbai", "Maharashtra Institute of Medical Science and Research, Latur", "Maharashtra Institute of Medical Education and Research (MIMER), Talegaon (D), Pune", "Terna Medical College, Navi Mumbai", "Indian Institute of Medical Science and Research, Jalna[26]", "All India Institute of Medical Science, Bhopal (AIIMS-Bhopal)", "Gandhi Medical College, Bhopal", "Shyam Shah Medical College, Rewa", "Gajara Raja Medical College, Gwalior", "Mahatma Gandhi Memorial Medical College, Indore", "Netaji Subhash Chandra Bose Medical College, Jabalpur", "People's Medical College, Bhopal", "Ruxmaniben Deepchand Gardi Medical College, Ujjain", "Jawaharlal Nehru Institute of Medical Sciences", "Regional Institute of Medical Sciences", "Shija Academy of Health Sciences", "North Eastern Indira Gandhi Regional Institute of Health and Medical Sciences", "Zoram Medical College", "Mon Medical College", "Nagaland Medical College", "All India Institute of Medical Sciences", "Army College of Medical Sciences", "University College of Medical Sciences", "Maulana Azad Medical College", "Lady Hardinge Medical College", "Atal Bihari Vajpayee Institute of Medical Sciences", "Vardhman Mahavir Medical College", "All India Institute of Medical Sciences, Bhubaneswar", "Acharya Harihar Postgraduate Institute of Cancer, Cuttack", "Srirama Chandra Bhanja Medical College and Hospital, Cuttack", "Maharaja Krushna Chandra Gajapati Medical College and Hospital, Brahmapur", "Veer Surendra Sai Institute of Medical Sciences and Research, Burla, Sambalpur", "Saheed Laxman Nayak Medical College and Hospital, Koraput", "Pandit Raghunath Murmu Medical College and Hospital, Baripada", "Fakir Mohan Medical College and Hospital, Balasore", "Bhima Bhoi Medical College and Hospital, Balangir", "Mahanadi Institute of Medical Science and Research, Talcher", "Sri Jagannath Medical College and Hospital, Puri", "Government Medical College and Hospital, Sundargarh", "Ispat Post Graduate Institute & Super Speciality Hospital, Rourkela", "Kalinga Institute of Medical Sciences, Bhubaneswar", "Institute of Medical Sciences and Sum Hospital, Bhubaneswar", "Hi-Tech Medical College & Hospital, Bhubaneswar", "Hi-Tech Medical College, Rourkela", "Jawaharlal Institute of Postgraduate Medical Education and Research", "Pondicherry Institute of Medical Sciences", "Mahatma Gandhi Medical College & Research Institute, Kirumampakkam, Pondicherry", "All India Institute of Medical Sciences, Bathinda", "Government Medical College, Amritsar", "Government Medical College, Patiala", "Christian Medical College, Ludhiana", "Adesh Institute of Medical Sciences & Research, Bathinda", "Guru Gobind Singh Medical College, Faridkot", "Punjab Institute of Medical Sciences, Jalandhar", "Dayanand Medical College", "All India Institute of Medical Sciences, Jodhpur", "Dr. S.N. Medical College, Jodhpur", "Geetanjali Medical College, Udaipur", "Government Medical College, Kota", "Jawaharlal Nehru Medical College, Ajmer", "Jhalawar Medical College, Jhalawar", "Pacific Medical College and Hospital, Udaipur", "Rabindranath Tagore Medical College", "Sardar Patel Medical College, Bikaner", "SMS Medical College, Jaipur", "Sikkim Manipal Institute of Medical Sciences, Gangtok", "Chengalpattu Medical College, Chengalpattu", "Christian Medical College, Vellore", "Coimbatore Medical College, Coimbatore", "Government Dharmapuri Medical College, Dharmapuri", "Government Sivagangai Medical College and Hospital, Sivagangai", "Government Tiruvannamalai Medical College and Hospital, Tiruvannamalai", "Government Vellore Medical College, Vellore", "Government Villupuram Medical College, Villupuram", "IRT Perundurai Medical College. Perundurai, Erode", "K.A.P. Viswanatham Government Medical College, Trichy, Tamil Nadu", "Kanyakumari Government Medical College, Kanyakumari", "Kilpauk Medical College, Chetput", "Madras Medical College, Chennai", "Madurai Medical College, Madurai", "Mohan Kumaramangalam Medical College, Salem, Tamil Nadu", "Rajah Muthiah Medical College, Chidambaram", "Sri Ramachandra Medical College and Research Institute, Chennai", "Stanley Medical College, Royapuram, Chennai", "Thanjavur Medical College, Thanjavur", "Thoothukudi Medical College, Thoothukudi", "Tirunelveli Medical College, Tirunelveli", "Nizam's Institute of Medical Sciences", "Osmania Medical College", "Gandhi Medical College", "Kakatiya Medical College, Warangal", "Government Medical College, Nizamabad", "Government Medical College, Mahbubnagar", "Government Medical College, Siddipet", "Government Medical College, Suryapet", "Government Medical College, Nalgonda", "Rajiv Gandhi Institute of Medical Sciences, Adilabad", "Employees State Insurance Corporation Medical College, Sanathnagar", "Apollo institute of Medical Sciences and Research", "Kamineni Institute of Medical Sciences, Narketpally", "Kamineni Academy of Medical Sciences, Hyderabad", "Malla Reddy Institute of Medical Sciences, Hyderabad", "Mallareddy Medical College for Women", "Prathima Institute of Medical Sciences, Karimnagar", "Chalmeda Anand Rao Institute of Medical Sciences, Karimnagar", "Dr. Patnam Mahender Reddy Institute of Medical Sciences", "Mamata Medical College, Khammam", "Mamata Academy of Medical Sciences", "Mediciti Institute Of Medical Sciences, Ghanpur", "M.N.R Medical College, Sangareddy", "S.V.S Medical College, Mahbubnagar", "Dr. VRK Womens Medical College", "Mahavir Institute of Medical Sciences, Vikarabad", "Maheshwara Medical College, Medak", "R.V.M Institute of Medical Sciences", "Surabhi Institute of Medical Sciences", "Bhaskar Medical College", "Shadan Institute of Medical Sciences", "Deccan College of Medical Sciences", "Ayaan Institute of Medical Sciences", "Agartala Government Medical College", "Tripura Medical College & Dr. B.R. Ambedkar Memorial Teaching Hospital", "All India Institute of Medical Sciences, Raebareli", "All India Institute of Medical Sciences, Gorakhpur", "Baba Raghav Das Medical College, Gorakhpur", "King Dashrath Medical College, Ayodhya", "Ganesh Shankar Vidyarthi Memorial Medical College, Kanpur", "Government Medical College, Azamgarh", "Government Medical College, Jalaun", "Government Medical College, Kannauj", "Institute of Medical Sciences, Banaras Hindu University, Varanasi", "Jawaharlal Nehru Medical College, Aligarh Muslim University, Aligarh", "Kansiram medical college, Saharanpur", "King George's Medical University (KGMU), Lucknow", "Lala Lajpat Rai Memorial Medical College, Meerut", "Maharani Laxmi Bai Medical College Jhansi", "Maharshi Vashishtha Autonomous State Medical College, Basti", "Motilal Nehru Medical College, Allahabad", "Muzaffarnagar Medical College, Muzaffarnagar", "Netaji Subhash Chandra Bose Subharti Medical college, Meerut, Uttar Pradesh", "Rajkiya Alopathic Medical College, Akbarpur, Ambedkar Nagar", "Sanjay Gandhi Post Graduate Institute of Medical Sciences", "Sarojini Naidu Medical College, Agra", "Uttar Pradesh University of Medical Sciences, Saifai, Etawah", "AIIMS Rishikesh", "Government Medical College, Haldwan", "Government Medical College, Srinagar, Pauri Garhwal, Srinagar", "Shri Guru Ram Rai Institute of Medical & Health Sciences, Patel Nagar, Dehradun", "Swami Rama Himalayan University, Doiwala", "Bankura Sammilani Medical College", "Burdwan Medical College", "Calcutta National Medical College", "College Of Medicine & JNM Hospital", "College of Medicine & Sagore Dutta Hospital", "Coochbehar Government Medical College and Hospital", "Diamond Harbour Government Medical College and Hospital", "ESIC Medical College, Kolkata", "ICARE Institute of Medical Sciences and Research", "IPGMER and SSKM Hospital", "IQ City Medical College", "Jagannath Gupta Institute of Medical Sciences and Hospital", "KPC Medical College and Hospital", "Malda Medical College and Hospital", "Medical College and Hospital, Kolkata", "Midnapore Medical College and Hospital", "Murshidabad Medical College", "Nil Ratan Sarkar Medical College and Hospital", "North Bengal Medical College", "Purulia Government Medical College and Hospital", "R. G. Kar Medical College and Hospital", "Raiganj Government Medical College and Hospital", "Rampurhat Government Medical College and Hospital", "Shri Ramkrishna Institute of Medical Sciences and Sanaka Hospital"};
        list_of_degrees = new ArrayAdapter<String>(DoctorFillForm.this, R.layout.list_item, listofdegrees);
        degree.setAdapter(list_of_degrees);
        specilization_list = new ArrayAdapter<String>(DoctorFillForm.this, R.layout.list_item, specialization);
        specializations.setAdapter(specilization_list);
        name_of_medical_college = new ArrayAdapter<String>(DoctorFillForm.this, R.layout.list_item, medicalCollegeNames);
        institute.setAdapter(name_of_medical_college);
        yearofcompletion = ArrayAdapter.createFromResource(DoctorFillForm.this, R.array.years, R.layout.list_item);
        year_of_comepletion.setAdapter(yearofcompletion);
        if (GoogleSignIn.getLastSignedInAccount(DoctorFillForm.this)!=null){
            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(DoctorFillForm.this);
            String user_id_google = GoogleSignIn.getLastSignedInAccount(DoctorFillForm.this).getId().toString();
            if (acct != null){
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
                Query checkUser = reference.orderByChild(user_id_google);
                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String FIRST_NAME = snapshot.child(user_id_google).child("first_name").getValue(String.class);
                        String LAST_NAME = snapshot.child(user_id_google).child("last_name").getValue(String.class);
                        String STATE = snapshot.child(user_id_google).child("states").getValue(String.class);
                        String DISTRICT = snapshot.child(user_id_google).child("districts").getValue(String.class);
                        stateofdoctorfill.setText(STATE);
                        districtofdoctorfill.setText(DISTRICT);
                        dummy_for_doctor_name.setText("Dr. "+FIRST_NAME+" "+LAST_NAME);
                        doctor_name.setText("Dr. "+FIRST_NAME+" "+LAST_NAME);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(DoctorFillForm.this, "Some thing went wrong", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }else{
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String user_uid = user.getUid().toString();
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
            Query checkUser = reference.orderByChild(user_uid);
            checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String FIRST_NAME = snapshot.child(user_uid).child("first_name").getValue(String.class);
                    String LAST_NAME = snapshot.child(user_uid).child("last_name").getValue(String.class);
                    String STATE = snapshot.child(user_uid).child("states").getValue(String.class);
                    String DISTRICT = snapshot.child(user_uid).child("districts").getValue(String.class);
                    stateofdoctorfill.setText(STATE);
                    districtofdoctorfill.setText(DISTRICT);
                    dummy_for_doctor_name.setText("Dr. "+FIRST_NAME+" "+LAST_NAME);
                    doctor_name.setText("Dr. "+FIRST_NAME+" "+LAST_NAME);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(DoctorFillForm.this, "Some thing went wrong", Toast.LENGTH_SHORT).show();
                }
            });
        }
        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!specializations.getText().toString().isEmpty()){
                    if (!degree.getText().toString().isEmpty()){
                        if (!institute.getText().toString().isEmpty()){
                            if (!year_of_comepletion.getText().toString().isEmpty()){
                                if (!yearofexpirirnce.getText().toString().isEmpty()){
                                    RadioButton radioButton = findViewById(radiogroup.getCheckedRadioButtonId());
                                    if (!radioButton.getText().toString().isEmpty()){
                                        if (!Image_Url.isEmpty()){
                                            uploadDoctorsinfo();
                                        }else{
                                            Snackbar snackbar = Snackbar.make(DoctorFillForm.this, findViewById(android.R.id.content), "Please go back and upload your image to complete your doctor profile", Snackbar.LENGTH_LONG);
                                            snackbar.show();
                                        }
                                    }else{
                                        Snackbar snackbar = Snackbar.make(DoctorFillForm.this, findViewById(android.R.id.content), "Please Select Your Eshtablishment Type", Snackbar.LENGTH_LONG);
                                        snackbar.show();
                                    }
                                }else
                                    {
                                    yearofexpirience_inputTextVew.setError("Please Enter Years Of Expirience You Have");
                                    yearofexpirience_inputTextVew.requestFocus();
                                }
                            }else
                                {
                                yearofcompletion_inputtext.setError("Please Select Your Year Of Completion");
                                yearofcompletion_inputtext.requestFocus();
                            }
                        }else
                            {
                            college_inputtext.setError("Please Select Your College/Institution");
                            college_inputtext.requestFocus();
                        }
                    }else
                        {
                        degree_inputtext.setError("Please Select Your Base Degree");
                        degree_inputtext.requestFocus();
                    }
                }else
                    {
                    specilization_inputtext.setError("Please Select Your Specilization");
                    specilization_inputtext.requestFocus();
                }
            }
        });
    }

    private void uploadDoctorsinfo() {
        String Specilization_ = specializations.getText().toString();
        String Degree_ = degree.getText().toString();
        String Institute_ = institute.getText().toString();
        String YearOfCompletetion = year_of_comepletion.getText().toString();
        String YearsOfExpirience = yearofexpirirnce.getText().toString();
        RadioButton radioButton = findViewById(radiogroup.getCheckedRadioButtonId());
        String EshtablishmentType = radioButton.getText().toString().toString();
        String States = stateofdoctorfill.getText().toString();
        String Districts = districtofdoctorfill.getText().toString();
        String Doctor_Name = dummy_for_doctor_name.getText().toString();
        String approved = "false";
        if (GoogleSignIn.getLastSignedInAccount(DoctorFillForm.this)==null){
            String user_id_for_profile = FirebaseAuth.getInstance().getCurrentUser().getUid();
            DatabaseReference doctorInfo = FirebaseDatabase.getInstance().getReference("Doctor").child(States).child(Districts).child(user_id_for_profile).child("EducationQualification");
            DatabaseReference doctorInfo1 = FirebaseDatabase.getInstance().getReference("Doctor").child(States).child(Districts).child(user_id_for_profile);
            DoctorUpload doctorUpload = new DoctorUpload(Specilization_, Degree_, Institute_, YearOfCompletetion,YearsOfExpirience,EshtablishmentType, approved,Image_Url,Doctor_Name,user_id_for_profile);
            doctorInfo1.setValue(doctorUpload);
            doctorInfo.setValue(doctorUpload).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Snackbar snackbar = Snackbar.make(DoctorFillForm.this, findViewById(android.R.id.content), "Information Saved Successfully", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    Intent mainactivity = new Intent(DoctorFillForm.this, DoctorFillForm2.class);
                    mainactivity.putExtra("State",States);
                    mainactivity.putExtra("District",Districts);
                    mainactivity.putExtra("Doctor Name",dummy_for_doctor_name.getText().toString());
                    startActivity(mainactivity);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(DoctorFillForm.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            String user_id_for_profile = GoogleSignIn.getLastSignedInAccount(DoctorFillForm.this).getId().toString();
            DatabaseReference doctorInfo = FirebaseDatabase.getInstance().getReference("Doctor").child(States).child(Districts).child(user_id_for_profile).child("EducationQualification");
            DatabaseReference doctorInfo1 = FirebaseDatabase.getInstance().getReference("Doctor").child(States).child(Districts).child(user_id_for_profile);
            DoctorUpload doctorUpload = new DoctorUpload(Specilization_, Degree_, Institute_, YearOfCompletetion,YearsOfExpirience,EshtablishmentType, approved,Image_Url,Doctor_Name,user_id_for_profile);
            doctorInfo1.setValue(doctorUpload);
            doctorInfo.setValue(doctorUpload).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Snackbar snackbar = Snackbar.make(DoctorFillForm.this, findViewById(android.R.id.content), "Information Saved Successfully", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    Intent mainactivity = new Intent(DoctorFillForm.this, DoctorFillForm2.class);
                    mainactivity.putExtra("State",States);
                    mainactivity.putExtra("District",Districts);
                    mainactivity.putExtra("Doctor Name",dummy_for_doctor_name.getText().toString());
                    startActivity(mainactivity);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(DoctorFillForm.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}