package com.kanad.health;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Locale;

public class AboutSectionForReport extends Fragment {
    View view;
    YouTubePlayerView youtube_player;
    ImageButton texttospeechbutton,stoptexttospeech;
    TextToSpeech toSpeech;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_about_section_for_report, container, false);
            TextView aboutdisease = view.findViewById(R.id.aboutdisease);
        TextView intenttogooglw = view.findViewById(R.id.intenttogooglw);
        youtube_player = view.findViewById(R.id.youtube_player);
        texttospeechbutton = view.findViewById(R.id.texttospeechbutton);
        stoptexttospeech = view.findViewById(R.id.stoptexttospeech);
        getLifecycle().addObserver(youtube_player);
        DiseaseReport activity = (DiseaseReport) getActivity();
        String Diseasename = activity.getMyData();

        intenttogooglw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.google.com/search?q="+Diseasename;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        switch (Diseasename){
            case "Fungal infection":
                aboutdisease.setText("Any disease caused by a fungus.\n" +
                        "A fungus that invades the tissue can cause a disease that's confined to the skin, spreads into tissue, bones and organs or affects the whole body.\n" +
                        "Symptoms depend on the area affected, but can include skin rash or vaginal infection resulting in abnormal discharge.\n" +
                        "Treatments include antifungal medication.");
                youtube_player.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        String videoId = "W0RVJpuZFIo";
                        youTubePlayer.loadVideo(videoId, 1);
                        youTubePlayer.pause();
                    }
                });
                break;
            case "Allergy":
                aboutdisease.setText("An allergy is a reaction your body has to a particular food or substance. Allergies are particularly common in children, but adults can get them too.\n" +
                        "Allergy symptoms include sneezing, wheezing and itchy eyes. A life-threatening allergic reaction can also cause swelling and breathing difficulties.\n" +
                        "If you have an allergy, try to avoid the thing you're allergic to if you can. Medicines like antihistamines can help ease symptoms if you need them.\n" +
                        "Allergies are caused by your immune system mistaking a harmless substance for something harmful. Common triggers include pollen, food and animals.");
                youtube_player.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        String videoId = "DbwuXLIvBHo";
                        youTubePlayer.loadVideo(videoId, 1);
                        youTubePlayer.pause();
                    }
                });
                break;
            case "GERD":
                aboutdisease.setText("Gastroesophageal reflux disease (GERD) occurs when stomach acid frequently flows back into the tube connecting your mouth and stomach (esophagus). This backwash (acid reflux) can irritate the lining of your esophagus.\n" +
                        "\n" +
                        "Many people experience acid reflux from time to time. GERD is mild acid reflux that occurs at least twice a week, or moderate to severe acid reflux that occurs at least once a week.\n" +
                        "\n" +
                        "Most people can manage the discomfort of GERD with lifestyle changes and over-the-counter medications. But some people with GERD may need stronger medications or surgery to ease symptoms.");
                youtube_player.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        String videoId = "IA7F6D196S8";
                        youTubePlayer.loadVideo(videoId, 1);
                        youTubePlayer.pause();
                    }
                });
                break;
            case "Chronic cholestasis":
                aboutdisease.setText("Cholestasis is a liver disease. It occurs when the flow of bile from your liver is reduced or blocked. Bile is fluid produced by your liver that aids in the digestion of food, especially fats. When bile flow is altered, it can lead to a buildup of bilirubin. Bilirubin is a pigment produced by your liver and excreted from your body via bile.\n" +
                        "\n" +
                        "There are two types of cholestasis: intrahepatic cholestasis and extrahepatic cholestasis. Intrahepatic cholestasis originates within the liver. It can be caused by:\n" +
                        "\n" +
                        "disease\n" +
                        "infection\n" +
                        "drug use\n" +
                        "genetic abnormalities\n" +
                        "hormonal effects on bile flow\n" +
                        "Pregnancy can also increase your risk for this condition.\n" +
                        "\n" +
                        "Extrahepatic cholestasis is caused by a physical barrier to the bile ducts. Blockages from things like gallstones, cysts, and tumors restrict the flow of bile.");
                youtube_player.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        String videoId = "8b1CFVvOAtY";
                        youTubePlayer.loadVideo(videoId, 1);
                        youTubePlayer.pause();
                    }
                });
                break;
            case "Drug Reaction":
                aboutdisease.setText("A drug allergy is the abnormal reaction of your immune system to a medication. Any medication — over-the-counter, prescription or herbal — is capable of inducing a drug allergy. However, a drug allergy is more likely with certain medications.\n" +
                        "\n" +
                        "The most common signs and symptoms of drug allergy are hives, rash or fever. A drug allergy may cause serious reactions, including a life-threatening condition that affects multiple body systems (anaphylaxis).\n" +
                        "\n" +
                        "A drug allergy is not the same as a drug side effect, a known possible reaction listed on a drug label. A drug allergy is also different from drug toxicity caused by an overdose of medication.");
                youtube_player.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        String videoId = "yCDE8KyZPwU";
                        youTubePlayer.loadVideo(videoId, 1);
                        youTubePlayer.pause();
                    }
                });
                break;
            case "Peptic ulcer diseae":
                aboutdisease.setText("Peptic ulcers are open sores that develop on the inside lining of your stomach and the upper portion of your small intestine. The most common symptom of a peptic ulcer is stomach pain.\n" +
                        "\n" +
                        "Peptic ulcers include:\n" +
                        "\n" +
                        "Gastric ulcers that occur on the inside of the stomach\n" +
                        "Duodenal ulcers that occur on the inside of the upper portion of your small intestine (duodenum)\n" +
                        "The most common causes of peptic ulcers are infection with the bacterium Helicobacter pylori (H. pylori) and long-term use of nonsteroidal anti-inflammatory drugs (NSAIDs) such as ibuprofen (Advil, Motrin IB, others) and naproxen sodium (Aleve). Stress and spicy foods do not cause peptic ulcers. However, they can make your symptoms worse.");
                youtube_player.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        String videoId = "w1BbdCfJ8rw";
                        youTubePlayer.loadVideo(videoId, 1);
                        youTubePlayer.pause();
                    }
                });
                break;
            case "AIDS":
                aboutdisease.setText("HIV causes AIDS and interferes with the body's ability to fight infections.\n" +
                        "The virus can be transmitted through contact with infected blood, semen or vaginal fluids.\n" +
                        "Within a few weeks of HIV infection, flu-like symptoms such as fever, sore throat and fatigue can occur. Then the disease is usually asymptomatic until it progresses to AIDS. AIDS symptoms include weight loss, fever or night sweats, fatigue and recurrent infections.\n" +
                        "No cure exists for AIDS, but strict adherence to antiretroviral regimens (ARVs) can dramatically slow the disease's progress as well as prevent secondary infections and complications.");
                youtube_player.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        String videoId = "LfpV9Ieslgw";
                        youTubePlayer.loadVideo(videoId, 1);
                        youTubePlayer.pause();
                    }
                });
                break;
            case "Diabetes":
                aboutdisease.setText("Diabetes mellitus, commonly known as diabetes, is a metabolic disease that causes high blood sugar. The hormone insulin moves sugar from the blood into your cells to be stored or used for energy. With diabetes, your body either doesn’t make enough insulin or can’t effectively use the insulin it does make.\n" +
                        "\n" +
                        "Untreated high blood sugar from diabetes can damage your nerves, eyes, kidneys, and other organs.\n" +
                        "\n" +
                        "There are a few different types of diabetes:\n" +
                        "\n" +
                        "Type 1 diabetes is an autoimmune disease. The immune system attacks and destroys cells in the pancreas, where insulin is made. It’s unclear what causes this attack. About 10 percent of people with diabetes have this type.\n" +
                        "Type 2 diabetes occurs when your body becomes resistant to insulin, and sugar builds up in your blood.\n" +
                        "Prediabetes occurs when your blood sugar is higher than normal, but it’s not high enough for a diagnosis of type 2 diabetes.\n" +
                        "Gestational diabetes is high blood sugar during pregnancy. Insulin-blocking hormones produced by the placenta cause this type of diabetes.\n" +
                        "A rare condition called diabetes insipidus is not related to diabetes mellitus, although it has a similar name. It’s a different condition in which your kidneys remove too much fluid from your body.\n" +
                        "\n" +
                        "Each type of diabetes has unique symptoms, causes, and treatments. Learn more about how these types differ from one another.");
                youtube_player.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        String videoId = "iUYpSvGOCV0";
                        youTubePlayer.loadVideo(videoId, 1);
                        youTubePlayer.pause();
                    }
                });
                break;
            case "Gastroenteritis":
                aboutdisease.setText("An intestinal infection marked by diarrhoea, cramps, nausea, vomiting and fever.\n" +
                        "Stomach flu is typically spread by contact with an infected person or through contaminated food or water.\n" +
                        "Diarrhoea, cramps, nausea, vomiting and low-grade fever are common symptoms.\n" +
                        "Avoiding contaminated food and water and washing hands can often help prevent infection. Rest and rehydration are the mainstays of treatment.");
                youtube_player.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        String videoId = "VBU83KYC9o8";
                        youTubePlayer.loadVideo(videoId, 1);
                        youTubePlayer.pause();
                    }
                });
                break;
            case "Bronchial Asthma":
                aboutdisease.setText("A condition in which a person's airways become inflamed, narrow and swell and produce extra mucus, which makes it difficult to breathe.\n" +
                        "Asthma can be minor or it can interfere with daily activities. In some cases, it may lead to a life-threatening attack.\n" +
                        "Asthma may cause difficulty breathing, chest pain, cough and wheezing. The symptoms may sometimes flare up.\n" +
                        "Asthma can usually be managed with rescue inhalers to treat symptoms (salbutamol) and controller inhalers that prevent symptoms (steroids). Severe cases may require longer-acting inhalers that keep the airways open (formoterol, salmeterol, tiotropium), as well as inhalant steroids.\n");
                youtube_player.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        String videoId = "HgGgk9mg8bI";
                        youTubePlayer.loadVideo(videoId, 1);
                        youTubePlayer.pause();
                    }
                });
                break;
            case "Hypertension":
                aboutdisease.setText("High blood pressure (hypertension) is a common condition in which the long-term force of the blood against your artery walls is high enough that it may eventually cause health problems, such as heart disease.\n" +
                        "\n" +
                        "Blood pressure is determined both by the amount of blood your heart pumps and the amount of resistance to blood flow in your arteries. The more blood your heart pumps and the narrower your arteries, the higher your blood pressure. A blood pressure reading is given in millimeters of mercury (mm Hg). It has two numbers.\n" +
                        "\n" +
                        "Top number (systolic pressure). The first, or upper, number measures the pressure in your arteries when your heart beats.\n" +
                        "Bottom number (diastolic pressure). The second, or lower, number measures the pressure in your arteries between beats.\n" +
                        "You can have high blood pressure for years without any symptoms. Uncontrolled high blood pressure increases your risk of serious health problems, including heart attack and stroke. Fortunately, high blood pressure can be easily detected. And once you know you have high blood pressure, you can work with your doctor to control it.");
                youtube_player.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        String videoId = "iVDkmEFSC1g";
                        youTubePlayer.loadVideo(videoId, 1);
                        youTubePlayer.pause();
                    }
                });
                break;
            case "Migraine":
                aboutdisease.setText("A migraine is a headache that can cause severe throbbing pain or a pulsing sensation, usually on one side of the head. It's often accompanied by nausea, vomiting, and extreme sensitivity to light and sound. Migraine attacks can last for hours to days, and the pain can be so severe that it interferes with your daily activities.\n" +
                        "\n" +
                        "For some people, a warning symptom known as an aura occurs before or with the headache. An aura can include visual disturbances, such as flashes of light or blind spots, or other disturbances, such as tingling on one side of the face or in an arm or leg and difficulty speaking.\n" +
                        "\n" +
                        "Medications can help prevent some migraines and make them less painful. The right medicines, combined with self-help remedies and lifestyle changes, might help.");
                youtube_player.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        String videoId = "MxCmMkXwiPk";
                        youTubePlayer.loadVideo(videoId, 1);
                        youTubePlayer.pause();
                    }
                });
                break;
            case "Cervical spondylosis":
                aboutdisease.setText("Cervical spondylosis is a general term for age-related wear and tear affecting the spinal disks in your neck. As the disks dehydrate and shrink, signs of osteoarthritis develop, including bony projections along the edges of bones (bone spurs).\n" +
                        "\n" +
                        "Cervical spondylosis is very common and worsens with age. More than 85 percent of people older than age 60 are affected by cervical spondylosis.\n" +
                        "\n" +
                        "Most people experience no symptoms from these problems. When symptoms do occur, nonsurgical treatments often are effective.");
                youtube_player.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        String videoId = "CCvhV3DK3T0";
                        youTubePlayer.loadVideo(videoId, 1);
                        youTubePlayer.pause();
                    }
                });
                break;
            case "Paralysis (brain hemorrhage)":
                aboutdisease.setText("Intracerebral hemorrhage (ICH) is when blood suddenly bursts into brain tissue, causing damage to your brain.\n" +
                        "\n" +
                        "Symptoms usually appear suddenly during ICH. They include headache, weakness, confusion, and paralysis, particularly on one side of your body. The buildup of blood puts pressure on your brain and interferes with its oxygen supply. This can quickly cause brain and nerve damage.\n" +
                        "\n" +
                        "This is a medical emergency requiring immediate treatment. ICH isn’t as common as ischemic stroke (which occurs when a blood vessel to your brain is blocked by a clot), but it’s more serious.\n" +
                        "\n" +
                        "Treatment depends on the amount of blood and the extent of brain injury that has occurred. Because the most common cause of ICH is related to high blood pressure, getting your blood pressure lowered and under control is the first key step. Sometimes surgery is required to relieve pressure from the accumulation of blood and to repair damaged blood vessels.\n" +
                        "\n" +
                        "Long-term treatment depends on the hemorrhage location and the amount of damage. Treatment may include physical, speech, and occupational therapy. Many people have some level of permanent disability.");
                youtube_player.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        String videoId = "Osc_O5T0L1I";
                        youTubePlayer.loadVideo(videoId, 1);
                        youTubePlayer.pause();
                    }
                });
                break;
            case "Jaundice":
                aboutdisease.setText("Jaundice is a condition in which the skin, sclera (whites of the eyes) and mucous membranes turn yellow. This yellow color is caused by a high level of bilirubin, a yellow-orange bile pigment. Bile is fluid secreted by the liver. Bilirubin is formed from the breakdown of red blood cells. \n"+
                        "Jaundice can be caused by a problem in any of the three phases in bilirubin production.\n" +
                        "\n" +
                        "Before the production of bilirubin, you may have what's called unconjugated jaundice due to increased levels of bilirubin caused by:\n" +
                        "\n" +
                        "Reabsorption of a large hematoma (a collection of clotted or partially clotted blood under the skin).\n" +
                        "Hemolytic anemias (blood cells are destroyed and removed from the bloodstream before their normal lifespan is over).\n" +
                        "During production of bilirubin, jaundice can be caused by:\n" +
                        "\n" +
                        "Viruses, including Hepatitis A, chronic Hepatitis B and C, and Epstein-Barr virus infection (infectious mononucleosis).\n" +
                        "Alcohol.\n" +
                        "Autoimmune disorders.\n" +
                        "Rare genetic metabolic defects.\n" +
                        "Medicines, including acetaminophen toxicity, penicillins, oral contraceptives, chlorpromazine (Thorazine®) and estrogenic or anabolic steroids.\n" +
                        "After bilirubin is produced, jaundice may be caused by obstruction (blockage) of the bile ducts from:\n" +
                        "\n" +
                        "Gallstones.\n" +
                        "Inflammation (swelling) of the gallbladder.\n" +
                        "Gallbladder cancer.\n" +
                        "Pancreatic tumor.");
                youtube_player.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        String videoId = "K5nBDll97i4";
                        youTubePlayer.loadVideo(videoId, 1);
                        youTubePlayer.pause();
                    }
                });
                break;
            case "Malaria":
                aboutdisease.setText("A disease caused by a plasmodium parasite, transmitted by the bite of infected mosquitoes.\n" +
                        "The severity of malaria varies based on the species of plasmodium.\n" +
                        "Symptoms are chills, fever and sweating, usually occurring a few weeks after being bitten.\n" +
                        "People travelling to areas where malaria is common typically take protective drugs before, during and after their trip. Treatment includes antimalarial drugs.");
                youtube_player.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        String videoId = "7BQAaBQ-X2s";
                        youTubePlayer.loadVideo(videoId, 1);
                        youTubePlayer.pause();
                    }
                });
                break;
            case "Chicken pox":
                aboutdisease.setText("A highly contagious viral infection which causes an itchy, blister-like rash on the skin.\n" +
                        "Chickenpox is highly contagious to those who haven't had the disease or been vaccinated against it.\n" +
                        "The most characteristic symptom is an itchy, blister-like rash on the skin.\n" +
                        "Chickenpox can be prevented by a vaccine. Treatment usually involves relieving symptoms, although high-risk groups may receive antiviral medication.");
                youtube_player.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        String videoId = "9vdXpt7s0m4";
                        youTubePlayer.loadVideo(videoId, 1);
                        youTubePlayer.pause();
                    }
                });
                break;
            case "Dengue":
                aboutdisease.setText("A mosquito-borne viral disease occurring in tropical and subtropical areas.\n" +
                        "Those who become infected with the virus a second time are at a significantly greater risk of developing severe disease.\n" +
                        "Symptoms include high fever, headache, rash and muscle and joint pain. In severe cases there is serious bleeding and shock, which can be life threatening.\n" +
                        "Treatment includes fluids and pain relievers. Severe cases require hospital care.");
                youtube_player.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        String videoId = "6nNiOFmmamg";
                        youTubePlayer.loadVideo(videoId, 1);
                        youTubePlayer.pause();
                    }
                });
                break;
            case "Typhoid":
                aboutdisease.setText("Typhoid fever is an infection that spreads through contaminated food and water.\n" +
                        "Vaccines are recommended in areas where typhoid fever is common.\n" +
                        "Symptoms include high fever, headache, stomach pain, weakness, vomiting and loose stools.\n" +
                        "Treatment includes antibiotics and fluids.");
                youtube_player.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        String videoId = "psJ5nnVTHKg";
                        youTubePlayer.loadVideo(videoId, 1);
                        youTubePlayer.pause();
                    }
                });
                break;
            case "hepatitis A":
                aboutdisease.setText("A highly contagious liver infection caused by the hepatitis A virus.\n" +
                        "Hepatitis A is preventable by vaccine. It spreads from contaminated food or water or contact with someone who is infected.\n" +
                        "Symptoms include fatigue, nausea, abdominal pain, loss of appetite and low-grade fever.\n" +
                        "The condition clears up on its own in one or two months. Rest and adequate hydration can help.");
                youtube_player.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        String videoId = "Gu_Nu-mf4bo";
                        youTubePlayer.loadVideo(videoId, 1);
                        youTubePlayer.pause();
                    }
                });
                break;
            case "Hepatitis B":
                aboutdisease.setText("A serious liver infection caused by the hepatitis B virus that's easily preventable by a vaccine.\n" +
                        "This disease is most commonly spread by exposure to infected bodily fluids.\n" +
                        "Symptoms are variable and include yellowing of the eyes, abdominal pain and dark urine. Some people, particularly children, don't experience any symptoms. In chronic cases, liver failure, cancer or scarring can occur.\n" +
                        "The condition often clears up on its own. Chronic cases require medication and possibly a liver transplant.");
                youtube_player.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        String videoId = "gXGxUPWg7XE";
                        youTubePlayer.loadVideo(videoId, 1);
                        youTubePlayer.pause();
                    }
                });
                break;
            case "Hepatitis C":
                aboutdisease.setText("An infection caused by a virus that attacks the liver and leads to inflammation.\n" +
                        "The virus is spread by contact with contaminated blood; for example, from sharing needles or from unsterile tattoo equipment.\n" +
                        "Most people have no symptoms. Those who do develop symptoms may have fatigue, nausea, loss of appetite and yellowing of the eyes and skin.\n" +
                        "Hepatitis C is treated with antiviral medication. In some people, newer medicines can eradicate the virus.");
                youtube_player.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        String videoId = "cLob8P21VEg";
                        youTubePlayer.loadVideo(videoId, 1);
                        youTubePlayer.pause();
                    }
                });
                break;
            case "Hepatitis D":
                aboutdisease.setText("A serious liver disease caused by infection with the hepatitis D virus.\n" +
                        "Hepatitis D only occurs amongst people who are infected with the Hepatitis B virus. Transmission requires contact with infectious blood. At-risk populations include intravenous drug abusers and men who have sex with men.\n" +
                        "Symptoms include abdominal pain, nausea and fatigue.\n" +
                        "There are few treatments specifically for hepatitis D, although different regimes may be tried. Management also focuses on supportive care.");
                youtube_player.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        String videoId = "l7kgoQwTWiY";
                        youTubePlayer.loadVideo(videoId, 1);
                        youTubePlayer.pause();
                    }
                });
                break;
            case "Hepatitis E":
                aboutdisease.setText("A liver disease caused by the hepatitis E virus.\n" +
                        "The hepatitis E virus is mainly transmitted through drinking water contaminated with faecal matter.\n" +
                        "Symptoms include jaundice, lack of appetite and nausea. In rare cases, it may progress to acute liver failure.\n" +
                        "Hepatitis E usually resolves on its own within four to six weeks. Treatment focuses on supportive care, rehydration and rest.");
                youtube_player.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        String videoId = "ZNqLqtxRaIg";
                        youTubePlayer.loadVideo(videoId, 1);
                        youTubePlayer.pause();
                    }
                });
                break;
            case "Alcoholic hepatitis":
                aboutdisease.setText("Liver inflammation caused by drinking too much alcohol.\n" +
                        "Alcoholic hepatitis can occur in people who drink heavily for many years.\n" +
                        "Symptoms include yellow skin and eyes along with increasing stomach size due to fluid accumulation.\n" +
                        "Treatment involves hydration, nutritional care and stopping alcohol use. Steroid drugs can help reduce liver inflammation.");
                youtube_player.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        String videoId = "6gKxsBWfAmA";
                        youTubePlayer.loadVideo(videoId, 1);
                        youTubePlayer.pause();
                    }
                });
                break;
            case "Tuberculosis":
                aboutdisease.setText("A potentially serious infectious bacterial disease that mainly affects the lungs.\n" +
                        "The bacteria that cause TB are spread when an infected person coughs or sneezes.\n" +
                        "Most people infected with the bacteria that cause tuberculosis don't have symptoms. When symptoms do occur, they usually include a cough (sometimes blood-tinged), weight loss, night sweats and fever.\n" +
                        "Treatment isn't always required for those without symptoms. Patients with active symptoms will require a long course of treatment involving multiple antibiotics.");
                youtube_player.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        String videoId = "x8uCWXAEd7s";
                        youTubePlayer.loadVideo(videoId, 1);
                        youTubePlayer.pause();
                    }
                });
                break;
            case "Common Cold":
                aboutdisease.setText("A common viral infection of the nose and throat.\n" +
                        "In contrast to the flu, a common cold can be caused by many different types of viruses. The condition is generally harmless and symptoms usually resolve within two weeks.\n" +
                        "Symptoms include a runny nose, sneezing and congestion. High fever or severe symptoms are reasons to see a doctor, especially in children.\n" +
                        "Most people recover on their own within two weeks. Over-the-counter products and home remedies can help control symptoms.");
                youtube_player.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        String videoId = "BVfnMCamGYM";
                        youTubePlayer.loadVideo(videoId, 1);
                        youTubePlayer.pause();
                    }
                });
                break;
            case "Pneumonia":
                aboutdisease.setText("Infection that inflames air sacs in one or both lungs, which may fill with fluid.\n" +
                        "With pneumonia, the air sacs may fill with fluid or pus. The infection can be life-threatening to anyone, but particularly to infants, children and people over 65.\n" +
                        "Symptoms include a cough with phlegm or pus, fever, chills and difficulty breathing.\n" +
                        "Antibiotics can treat many forms of pneumonia. Some forms of pneumonia can be prevented by vaccines.");
                youtube_player.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        String videoId = "RTS3yNM-xKc";
                        youTubePlayer.loadVideo(videoId, 1);
                        youTubePlayer.pause();
                    }
                });
                break;
            case "Dimorphic hemmorhoids(piles)":
                aboutdisease.setText("Swollen and inflamed veins in the rectum and anus that cause discomfort and bleeding.\n" +
                        "Haemorrhoids are usually caused by straining during bowel movements, obesity or pregnancy.\n" +
                        "Discomfort is a common symptom, especially during bowel movements or when sitting. Other symptoms include itching and bleeding.\n" +
                        "A high-fibre diet can be effective, along with stool softeners. In some cases, a medical procedure to remove the haemorrhoid may be needed to provide relief.");
                youtube_player.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        String videoId = "BDoCOENmLG0";
                        youTubePlayer.loadVideo(videoId, 1);
                        youTubePlayer.pause();
                    }
                });
                break;
            case "Heart attack":
                aboutdisease.setText("A blockage of blood flow to the heart muscle.\n" +
                        "A heart attack is a medical emergency. A heart attack usually occurs when a blood clot blocks blood flow to the heart. Without blood, tissue loses oxygen and dies.\n" +
                        "Symptoms include tightness or pain in the chest, neck, back or arms, as well as fatigue, lightheadedness, abnormal heartbeat and anxiety. Women are more likely to have atypical symptoms than men.\n" +
                        "Treatment ranges from lifestyle changes and cardiac rehabilitation to medication, stents and bypass surgery.");
                youtube_player.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        String videoId = "TDXH3QSpFcM";
                        youTubePlayer.loadVideo(videoId, 1);
                        youTubePlayer.pause();
                    }
                });
                break;
            case "Varicose veins":
                aboutdisease.setText("Gnarled, enlarged veins, most commonly appearing in the legs and feet.\n" +
                        "Varicose veins are generally benign. The cause of this condition is not known.\n" +
                        "For many people, there are no symptoms and varicose veins are simply a cosmetic concern. In some cases, they cause aching pain and discomfort or signal an underlying circulatory problem.\n" +
                        "Treatment involves compression stockings, exercise or procedures to close or remove the veins.\n");
                youtube_player.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        String videoId = "iTCj2fhOStM";
                        youTubePlayer.loadVideo(videoId, 1);
                        youTubePlayer.pause();
                    }
                });
                break;
            case "Hypothyroidism":
                aboutdisease.setText("A condition in which the thyroid gland doesn't produce enough thyroid hormone.\n" +
                        "Hypothyroidism's deficiency of thyroid hormones can disrupt such things as heart rate, body temperature and all aspects of metabolism. Hypothyroidism is most prevalent in older women.\n" +
                        "Major symptoms include fatigue, cold sensitivity, constipation, dry skin and unexplained weight gain.\n" +
                        "Treatment consists of thyroid hormone replacement.\n");
                youtube_player.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        String videoId = "gTrXfHRylr8";
                        youTubePlayer.loadVideo(videoId, 1);
                        youTubePlayer.pause();
                    }
                });
                break;
            case "Hyperthyroidism":
                aboutdisease.setText("The overproduction of a hormone by the butterfly-shaped gland in the neck (thyroid).\n" +
                        "Hyperthyroidism is the production of too much thyroxine hormone. It can increase metabolism.\n" +
                        "Symptoms include unexpected weight loss, rapid or irregular heartbeat, sweating and irritability, although the elderly often experience no symptoms.\n" +
                        "Treatments include radioactive iodine, medication and sometimes surgery.");
                youtube_player.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        String videoId = "436hq3hw2Rw";
                        youTubePlayer.loadVideo(videoId, 1);
                        youTubePlayer.pause();
                    }
                });
                break;
            case "Hypoglycemia":
                aboutdisease.setText("Low blood sugar, the body's main source of energy.\n" +
                        "Diabetes treatment and other conditions can cause hypoglycaemia.\n" +
                        "Confusion, heart palpitations, shakiness and anxiety are symptoms.\n" +
                        "Consuming high-sugar foods or drinks, such as orange juice or regular fizzy drinks, can treat this condition. Alternatively, medication can be used to raise blood sugar levels. It's also important that a doctor identifies and treats the underlying cause.");
                youtube_player.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        String videoId = "W3GNzkfCisE";
                        youTubePlayer.loadVideo(videoId, 1);
                        youTubePlayer.pause();
                    }
                });
                break;
            case "Osteoarthristis":
                aboutdisease.setText("A type of arthritis that occurs when flexible tissue at the ends of bones wears down.\n" +
                        "The wearing down of the protective tissue at the ends of bones (cartilage) occurs gradually and worsens over time.\n" +
                        "Joint pain in the hands, neck, lower back, knees or hips is the most common symptom.\n" +
                        "Medication, physiotherapy and sometimes surgery can help reduce pain and maintain joint movement.");
                youtube_player.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        String videoId = "Bn25nMpgzno";
                        youTubePlayer.loadVideo(videoId, 1);
                        youTubePlayer.pause();
                    }
                });
                break;
            case "Arthritis":
                aboutdisease.setText("Inflammation of one or more joints, causing pain and stiffness that can worsen with age.\n" +
                        "Different types of arthritis exist, each with different causes including wear and tear, infections and underlying diseases.\n" +
                        "Symptoms include pain, swelling, reduced range of motion and stiffness.\n" +
                        "Medication, physiotherapy or sometimes surgery helps reduce symptoms and improve quality of life.");
                youtube_player.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        String videoId = "ZFMfVoHmQlI";
                        youTubePlayer.loadVideo(videoId, 1);
                        youTubePlayer.pause();
                    }
                });
                break;
            case "(vertigo) Paroymsal  Positional Vertigo":
                aboutdisease.setText("Episodes of dizziness and a sensation of spinning with certain head movements.\n" +
                        "Benign paroxysmal positional vertigo (BPPV) is triggered by certain changes in head position, such as tipping the head up or down. It's rarely serious unless it increases the risk of falling.\n" +
                        "People can experience dizziness, a spinning sensation (vertigo), lightheadedness, unsteadiness, loss of balance and nausea.\n" +
                        "Treatment includes a series of head movements that shift particles in the ears.");
                youtube_player.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        String videoId = "o15m49aMDG8";
                        youTubePlayer.loadVideo(videoId, 1);
                        youTubePlayer.pause();
                    }
                });
                break;
            case "Acne":
                aboutdisease.setText("A skin condition that occurs when hair follicles plug with oil and dead skin cells.\n" +
                        "Acne is most common in teenagers and young adults.\n" +
                        "Symptoms range from uninflamed blackheads to pus-filled pimples or large, red and tender bumps.\n" +
                        "Treatments include over-the-counter creams and cleanser, as well as prescription antibiotics.");
                youtube_player.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        String videoId = "Tvybaq7UabY";
                        youTubePlayer.loadVideo(videoId, 1);
                        youTubePlayer.pause();
                    }
                });
                break;
            case "Urinary tract infection":
                aboutdisease.setText("An infection in any part of the urinary system, the kidneys, bladder or urethra.\n" +
                        "Urinary tract infections are more common in women. They usually occur in the bladder or urethra, but more serious infections involve the kidney.\n" +
                        "A bladder infection may cause pelvic pain, increased urge to urinate, pain with urination and blood in the urine. A kidney infection may cause back pain, nausea, vomiting and fever.\n" +
                        "Common treatment is with antibiotics.");
                youtube_player.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        String videoId = "1lPxZIjNERg";
                        youTubePlayer.loadVideo(videoId, 1);
                        youTubePlayer.pause();
                    }
                });
                break;
            case "Psoriasis":
                aboutdisease.setText("A condition in which skin cells build up and form scales and itchy, dry patches.\n" +
                        "Psoriasis is thought to be an immune system problem. Triggers include infections, stress and cold.\n" +
                        "The most common symptom is a rash on the skin, but sometimes the rash involves the nails or joints.\n" +
                        "Treatment aims to remove scales and stop skin cells from growing so quickly. Topical ointments, light therapy and medication can offer relief.");
                youtube_player.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        String videoId = "7ZdAI6qQtiM";
                        youTubePlayer.loadVideo(videoId, 1);
                        youTubePlayer.pause();
                    }
                });
                break;
            case "Impetigo":
                aboutdisease.setText("A highly contagious skin infection that causes red sores on the face.\n" +
                        "Impetigo mainly affects infants and children.\n" +
                        "The main symptom is red sores that form around the nose and mouth. The sores rupture, ooze for a few days, then form a yellow-brown crust.\n" +
                        "Antibiotics shorten the infection and can help prevent spread to others.");
                youtube_player.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        String videoId = "sWsvDRmrEVY";
                        youTubePlayer.loadVideo(videoId, 1);
                        youTubePlayer.pause();
                    }
                });
                break;
        }
        String articletoread = aboutdisease.getText().toString();
        toSpeech = new TextToSpeech(getActivity().getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i!=TextToSpeech.ERROR){
                    toSpeech.setLanguage(Locale.ENGLISH);
                }
            }
        });
        texttospeechbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toSpeech.speak(articletoread,TextToSpeech.QUEUE_FLUSH,null);
                texttospeechbutton.setVisibility(View.GONE);
                stoptexttospeech.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(), "Starting", Toast.LENGTH_SHORT).show();
            }
        });

        stoptexttospeech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toSpeech.stop();
                texttospeechbutton.setVisibility(View.VISIBLE);
                stoptexttospeech.setVisibility(View.GONE);
            }
        });

        return view;
    }
}