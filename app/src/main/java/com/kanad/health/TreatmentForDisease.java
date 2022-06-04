package com.kanad.health;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;

import java.util.Locale;

public class TreatmentForDisease extends Fragment {
    TextView abouttreatment,talktoadoctor;
    TextView buymedicine,url;
    TextToSpeech toSpeech;
    ImageButton texttospeechbutton_treatment,stoptexttospeech_treatment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_treatment_for_disease, container, false);
        DiseaseReport activity = (DiseaseReport) getActivity();
        texttospeechbutton_treatment = view.findViewById(R.id.texttospeechbutton_treatment);
        stoptexttospeech_treatment = view.findViewById(R.id.stoptexttospeech_treatment);
        abouttreatment = view.findViewById(R.id.abouttreatment);
        talktoadoctor = view.findViewById(R.id.talktoadoctor);
        String Diseasename = activity.getMyData();
        buymedicine = view.findViewById(R.id.buymedicine);
        url = view.findViewById(R.id.url);

        talktoadoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(),DoctorShow.class);
                startActivity(i);
            }
        });


        buymedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url.getText().toString()));
                startActivity(i);
            }
        });
        switch (Diseasename){
            case "Fungal infection":
                abouttreatment.setText("When to see a doctor\n" +
                        "Many types of fungal skin infections eventually improve in response to over-the-counter (OTC) fungal treatments. However, call your doctor if you:\n" +
                        "\n" +
                        "have a fungal skin infection that doesn’t improve, gets worse, or returns after OTC treatment\n" +
                        "notice patches of hair loss along with itchiness or scaly skin\n" +
                        "have a weakened immune system and suspect a fungal infection\n" +
                        "have diabetes and think you have athlete’s foot or onychomycosis\n" +
                        "Skin fungus treatment\n" +
                        "Antifungal medications work to treat fungal infections. They can either kill fungi directly or prevent them from growing and thriving. Antifungal drugs are available as OTC treatments or prescription medications, and come in a variety of forms, including:\n" +
                        "\n" +
                        "creams or ointments\n" +
                        "pills\n" +
                        "powders\n" +
                        "sprays\n" +
                        "shampoos\n" +
                        "If you suspect you have a fungal skin infection, you may want to try an OTC product to see if it helps clear up the condition. In more persistent or severe cases, your doctor may prescribe a stronger antifungal drug to help treat your infection.\n" +
                        "\n" +
                        "In addition to taking OTC or prescription antifungals, there are some things that you can do at home to help get rid of the fungal infection. These include:\n" +
                        "\n" +
                        "keeping the affected area clean and dry\n" +
                        "wearing loose-fitting clothing or shoes that allow your skin to breathe"+"Prevention\n" +
                        "Try to keep the following tips in mind to help prevent a fungal skin infection from developing:\n" +
                        "\n" +
                        "Be sure to practice good hygiene.\n" +
                        "Don’t share clothing, towels, or other personal items.\n" +
                        "Wear clean clothes every day, particularly socks and underwear.\n" +
                        "Choose clothing and shoes that breathe well. Avoid clothing or shoes that are too tight or have a restrictive fit.\n" +
                        "Make sure to dry off properly with a clean, dry, towel after showering, bathing, or swimming.\n" +
                        "Wear sandals or flip-flops in locker rooms instead of walking with bare feet.\n" +
                        "Wipe down shared surfaces, such as gym equipment or mats.\n" +
                        "Stay away from animals that have signs of a fungal infection, such as missing fur or frequent scratching.");
                url.setText("https://amzn.to/3Mdze3y");
                buymedicine.setVisibility(View.VISIBLE);
                break;
            case "Allergy":
                abouttreatment.setText("Treatment\n" +
                        "Allergy treatments include:\n" +
                        "\n" +
                        "Allergen avoidance. Your doctor will help you take steps to identify and avoid your allergy triggers. This is generally the most important step in preventing allergic reactions and reducing symptoms.\n" +
                        "Medications. Depending on your allergy, medications can help reduce your immune system reaction and ease symptoms. Your doctor might suggest over-the-counter or prescription medication in the form of pills or liquid, nasal sprays, or eyedrops.\n" +
                        "Immunotherapy. For severe allergies or allergies not completely relieved by other treatment, your doctor might recommend allergen immunotherapy. This treatment involves a series of injections of purified allergen extracts, usually given over a period of a few years.\n" +
                        "\n" +
                        "Another form of immunotherapy is a tablet that's placed under the tongue (sublingual) until it dissolves. Sublingual drugs are used to treat some pollen allergies.\n" +
                        "\n" +
                        "Emergency epinephrine. If you have a severe allergy, you might need to carry an emergency epinephrine shot at all times. Given for severe allergic reactions, an epinephrine shot (Auvi-Q, EpiPen, others) can reduce symptoms until you get emergency treatment.");
                break;
            case "GERD":
                abouttreatment.setText("Your doctor is likely to recommend that you first try lifestyle modifications and over-the-counter medications. If you don't experience relief within a few weeks, your doctor might recommend prescription medication or surgery.\n" +
                        "\n" +
                        "Over-the-counter medications\n" +
                        "The options include:\n" +
                        "\n" +
                        "Antacids that neutralize stomach acid. Antacids, such as Mylanta, Rolaids and Tums, may provide quick relief. But antacids alone won't heal an inflamed esophagus damaged by stomach acid. Overuse of some antacids can cause side effects, such as diarrhea or sometimes kidney problems.\n" +
                        "Medications to reduce acid production. These medications — known as H-2-receptor blockers — include cimetidine (Tagamet HB), famotidine (Pepcid AC) and nizatidine (Axid AR). H-2-receptor blockers don't act as quickly as antacids, but they provide longer relief and may decrease acid production from the stomach for up to 12 hours. Stronger versions are available by prescription.\n" +
                        "Medications that block acid production and heal the esophagus. These medications — known as proton pump inhibitors — are stronger acid blockers than H-2-receptor blockers and allow time for damaged esophageal tissue to heal. Over-the-counter proton pump inhibitors include lansoprazole (Prevacid 24 HR) and omeprazole (Prilosec OTC, Zegerid OTC).\n" +
                        "Prescription medications\n" +
                        "Prescription-strength treatments for GERD include:\n" +
                        "\n" +
                        "Prescription-strength H-2-receptor blockers. These include prescription-strength famotidine (Pepcid) and nizatidine. These medications are generally well-tolerated but long-term use may be associated with a slight increase in risk of vitamin B-12 deficiency and bone fractures.\n" +
                        "Prescription-strength proton pump inhibitors. These include esomeprazole (Nexium), lansoprazole (Prevacid), omeprazole (Prilosec, Zegerid), pantoprazole (Protonix), rabeprazole (Aciphex) and dexlansoprazole (Dexilant). Although generally well-tolerated, these medications might cause diarrhea, headache, nausea and vitamin B-12 deficiency. Chronic use might increase the risk of hip fracture.\n" +
                        "Medication to strengthen the lower esophageal sphincter. Baclofen may ease GERD by decreasing the frequency of relaxations of the lower esophageal sphincter. Side effects might include fatigue or nausea.");
                break;
            case "Chronic cholestasis":
                abouttreatment.setText("The goals of treatment for cholestasis of pregnancy are to ease itching and prevent complications in your baby.\n" +
                        "\n" +
                        "Ease itching\n" +
                        "To soothe intense itching, your pregnancy care provider may recommend:\n" +
                        "\n" +
                        "Taking a prescription drug called ursodiol (Actigall, Urso, Urso Forte), which helps to lower the level of bile in your blood. Other medications to relieve itching may also be an option.\n" +
                        "Soaking itchy areas in cool or lukewarm water.\n" +
                        "It's best to talk to your pregnancy care provider before you start any medications for treating itching.\n" +
                        "\n" +
                        "Monitoring your baby's health\n" +
                        "Cholestasis of pregnancy can potentially cause complications to your pregnancy. Your pregnancy care provider may recommend close monitoring of your baby while you're pregnant.\n" +
                        "\n" +
                        "Monitoring and treatment may include:\n" +
                        "\n" +
                        "Nonstress testing. During a nonstress test, your pregnancy care provider will check your baby's heart rate, and how much his or her heart rate increases with activity.\n" +
                        "Fetal biophysical profile (BPP). This series of tests helps monitor your baby's well-being. It provides information about your baby's movement, muscle tone, breathing and amount of amniotic fluid. While the results of a nonstress test or BPP can be reassuring, they can't really predict the risk of preterm birth or other complications associated with cholestasis of pregnancy.\n" +
                        "Early induction of labor. Even if the prenatal tests appear normal, your pregnancy care provider may suggest inducing labor before your due date, given the risk of stillbirth.");
                break;
            case "Drug Reaction":
                abouttreatment.setText("Treating allergic reactions\n" +
                        "Many mild to moderate allergic reactions can be treated at home or with OTC medications. The following treatments are commonly used to reduce the symptoms of an allergic reaction:\n" +
                        "\n" +
                        "Antihistamines\n" +
                        "Antihistamines can help to treat most minor allergic reactions regardless of the cause. These drugs reduce the body’s production of histamine, which reduces all symptoms, including sneezing, watering eyes, and skin reactions.\n" +
                        "\n" +
                        "Second-generation antihistamines, including Claritin (loratadine) and Zyrtec (cetirizine), are less likely to cause drowsiness than first-generation antihistamines, such as Benadryl.\n" +
                        "\n" +
                        "Antihistamines come in several forms, usually to help deliver the medication closer to the source of the reaction or make it easier to consume, such as:\n" +
                        "\n" +
                        "oral pills\n" +
                        "dissolvable tablets\n" +
                        "nasal sprays\n" +
                        "liquids\n" +
                        "eye drops\n" +
                        "Antihistamines in these forms are available from pharmacies, to buy online, or on prescription from a doctor.\n" +
                        "\n" +
                        "Antihistamines can also be taken to prevent allergies. Many people with seasonal or pet allergies will begin taking antihistamines when they know they are going to be exposed to an allergen.\n" +
                        "\n" +
                        "A person who is pregnant or has a liver disorder should consult their doctor before taking antihistamines.\n" +
                        "\n" +
                        "Nasal decongestants\n" +
                        "Nasal decongestant pills, liquids, and sprays can also help reduce stuffy, swollen sinuses and related symptoms, such as a sore throat or coughing.\n" +
                        "\n" +
                        "However, decongestant medications should not be taken continuously for more than 72 hours.\n" +
                        "\n" +
                        "Nasal decongestants are available over the counter and online.\n" +
                        "\n" +
                        "Anti-inflammatory medication\n" +
                        "Non-steroidal anti-inflammatory medications (NSAIDs) may also be used to help temporarily reduce pain, swelling, and cramping caused by allergies.\n" +
                        "\n" +
                        "Avoid the allergen\n" +
                        "The best way to treat and prevent allergic reactions is to know what triggers the reaction and stay away from it, especially food allergens.\n" +
                        "\n" +
                        "When this is not possible or realistic, using antihistamines or decongestants when in contact with allergens can help to treat the symptoms.");
                break;
            case "Peptic ulcer diseae":
                abouttreatment.setText("1. Flavonoids\n" +
                        "ResearchTrusted Source suggests that flavonoids, also known as bioflavonoids, may be an effective additional treatment for stomach ulcers.\n" +
                        "\n" +
                        "Flavonoids are compounds that occur naturally in many fruits and vegetables. Foods and drinks rich in flavonoids include:\n" +
                        "\n" +
                        "soybeans\n" +
                        "legumes\n" +
                        "red grapes\n" +
                        "kale\n" +
                        "broccoli\n" +
                        "apples\n" +
                        "berries\n" +
                        "teas, especially green tea\n" +
                        "These foods may also help the body fight against the H. pylori bacteria.\n" +
                        "\n" +
                        "Flavonoids are referred to as “gastroprotectiveTrusted Source,” which means they defend the lining of the stomach and could allow ulcers to heal.\n" +
                        "\n" +
                        "According to the Linus Pauling Institute, there are no side effects of consuming flavonoids in the amount found in a typical diet, but higher amounts of flavonoids may interfere with blood clotting.\n" +
                        "\n" +
                        "You can get flavonoids in your diet or take them as supplements."+"2. Deglycyrrhizinated licorice\n" +
                        "Don’t let that long first word give you a stomachache. Deglycyrrhizinated licorice is just plain old licorice with the sweet flavor extracted. One studyTrusted Source showed that deglycyrrhizinated licorice might help ulcers heal by inhibiting the growth of H. pylori.\n" +
                        "\n" +
                        "Deglycyrrhizinated licorice is available as a supplement.\n" +
                        "\n" +
                        "You can’t get this effect from eating licorice candy though. Too much licorice candy can be bad for some peopleTrusted Source. Consuming more than 2 ounces daily for more than two weeks can make existing heart problems or high blood pressure worse.\n" +
                        "\n" +
                        "3. Probiotics\n" +
                        "Probiotics are the living bacteria and yeast that provide healthy and important microorganisms to your digestive tract. They are present in many common foods, particularly fermented foods. These include:\n" +
                        "\n" +
                        "buttermilk\n" +
                        "yogurt\n" +
                        "miso\n" +
                        "kimchi\n" +
                        "kefir\n" +
                        "You can also take probiotics in supplement form.\n" +
                        "\n" +
                        "Studies have shown that probiotics may be helpful in wiping out H. pylori and increasing the recovery rateTrusted Source for people with ulcers when added to the traditional regimen of antibiotics.\n" +
                        "\n" +
                        "4. Honey\n" +
                        "Honey is far from simply sweet.\n" +
                        "\n" +
                        "Depending on the plant it’s derived from, honey can contain up to 200 elements, including polyphenols and other antioxidants. HoneyTrusted Source is a powerful antibacterial and has been shown to inhibit H. pylori growth.\n" +
                        "\n" +
                        "As long as you have normal blood sugar levels, you can enjoy honey as you would any sweetener, with the bonus of perhaps soothing your ulcers.\n" +
                        "\n" +
                        "\n" +
                        "5. Garlic\n" +
                        "Garlic extract has been shown to inhibit H. pylori growth in lab, animal, and human trials.\n" +
                        "\n" +
                        "If you don’t like the taste (and lingering aftertaste) of garlic, you can take garlic extract in supplement form.\n" +
                        "\n" +
                        "Garlic acts as a blood thinner, so ask your doctor before taking it if you use warfarin (Coumadin), other prescription blood thinners, or aspirin.\n" +
                        "\n" +
                        "6. Cranberry\n" +
                        "Cranberry has been shown in some studiesTrusted Source to help decrease urinary tract infections by preventing bacteria from settling on the walls of the bladder. Cranberry and cranberry extract also may help fight H. pyloriTrusted Source.\n" +
                        "\n" +
                        "You can drink cranberry juice, eat cranberries, or take cranberry supplements.\n" +
                        "\n" +
                        "No specific amount of consumption is associated with relief. Too much cranberry in any form may cause stomach and intestinal discomfort due to its high sugar content, so start with small amounts and increase gradually.\n" +
                        "\n" +
                        "Many commercial cranberry juices are heavily sweetened with sugar or high fructose corn syrup, which can also add empty calories. Avoid those juices by buying juice sweetened only by other juices.\n" +
                        "\n" +
                        "7. Mastic\n" +
                        "Mastic is the sap of a tree grown in the Mediterranean.\n" +
                        "\n" +
                        "Studies of the effectiveness of mastic on H. pylori infection are mixed, but at least one small studyTrusted Source shows that chewing mastic gum may help fight H. pylori, getting rid of the bacteria in about 3 out of 10 people who used it.\n" +
                        "\n" +
                        "However, when compared to the traditional combination of antibiotics and acid-blocking medications, the gum was significantly less effective than the medications. The traditional treatment got rid of the bacteria in more than 75 percent of the people studied.\n" +
                        "\n" +
                        "You can chew the gum or swallow mastic in supplement form.\n" +
                        "\n" +
                        "8. Fruits, vegetables, and whole grains\n" +
                        "A diet centered on fruits, vegetables, and whole grains is not only good for your overall health. According to the Mayo Clinic, a vitamin-rich diet can help your body heal your ulcer.\n" +
                        "\n" +
                        "Foods containing the antioxidant polyphenolsTrusted Source may protect you from ulcers and help ulcers heal. Polyphenol-rich foods and seasonings include:\n" +
                        "\n" +
                        "dried rosemary\n" +
                        "flaxseed\n" +
                        "Mexican oregano\n" +
                        "dark chocolate\n" +
                        "blueberries, raspberries, strawberries, elderberries, and blackberries\n" +
                        "black olives\n" +
                        "Foods to limit or avoid with ulcers and acid reflux\n" +
                        "Some people with ulcers also have acid reflux disease.\n" +
                        "\n" +
                        "In some people, certain foods can affect the lower part of the esophagus, called the lower esophageal sphincter (LES), allowing acid and stomach contents to back up into the esophagus. This can cause injury to the esophagus, as well as heartburn, indigestion, and other discomfort.\n" +
                        "\n" +
                        "To reduce acid reflux pain, you may want to limit:\n" +
                        "\n" +
                        "coffee and other caffeinated beverages\n" +
                        "carbonated beverages\n" +
                        "chocolate\n" +
                        "chilies and hot peppers\n" +
                        "processed foods\n" +
                        "foods with a high amount of salt\n" +
                        "deep-fried foods\n" +
                        "acidic foods like citrus and tomatoes\n" +
                        "Overeating and eating within two to three hours of going to bed may also worsen the symptoms of acid reflux.\n" +
                        "\n" +
                        "Not every food acts the same for every person, so keeping track of which foods seem to make acid reflux symptoms worse can be helpful.\n" +
                        "\n" +
                        "Alcohol\n" +
                        "Having more than one drink a day for women and more than two for men is considered excessive drinkingTrusted Source.\n" +
                        "\n" +
                        "If a couple of drinks after work is how you unwind, you might want to consider a healthier alternative. Regular alcohol use causes significant stomach inflammation.\n" +
                        "\n" +
                        "Also, alcohol is another substance that can relax the lower part of the esophagus, increasing your risk for acid reflux.");
                break;
            case "AIDS":
                abouttreatment.setText("Body therapies\n" +
                        "Yoga and massage therapy may help reduce pain for some people. ResearchTrusted Source has shown that yoga can also improve feelings of overall health and reduce anxiety and depression. It has even been shown to improve levels of CD4 cells, which are immune cells that are attacked by HIV.\n" +
                        "\n" +
                        "Acupuncture may help with nausea and other treatment side effects. Acupuncture is an ancient Chinese medical practice that involves placing thin, solid needles into various pressure points on the body. This can release chemicals in the body that can help relieve pain.\n" +
                        "\n" +
                        "Relaxation therapies\n" +
                        "Meditation and other forms of relaxation treatment can help reduce anxiety. They may improve the ability to cope with the stress of a chronic illness such as HIV.\n" +
                        "\n" +
                        "Herbal medicine\n" +
                        "Herbal medications should be used with caution. There isn’t enough evidence to support the use of these drugs for relieving HIV symptoms.\n" +
                        "\n" +
                        "However, a brief course of certain herbs may support immunity in people with HIV. Research has shown that milk thistle is one example. Milk thistle is a common herb used in people to improve liver function and does not interact significantly with antivirals. Keep in mind though that other herbs may interact with conventional HIV treatments.\n" +
                        "\n" +
                        "People with HIV should tell their healthcare provider before using any herbal treatments. This allows their provider to monitor for any drug interactions or side effects.\n" +
                        "\n" +
                        "Medical marijuana\n" +
                        "Loss of appetite is common in people with HIV. And some antiviral medications can upset the stomach and make it harder to keep up with scheduled medication doses. Marijuana can help reduce pain, control nausea, and increase appetite. However, medical marijuana is legal only in certain states. In addition, smoking marijuana is associated with many of the same health risks as the smoking of any substance. A healthcare provider can provide more information.\n" +
                        "\n" +
                        "There’s little evidence to suggest that medical marijuana will interact with modern HIV management medications. Still, people with HIV should consult their healthcare provider before using marijuana to treat their symptoms. The provider will monitor for possible drug interactions or respiratory complications.\n" +
                        "\n" +
                        "\n" +
                        "Interactions between supplements and HIV treatment\n" +
                        "Supplements should be used with caution by people living with HIV or AIDS. Some supplements may be safe to use, while others could cause problems. People with HIV or AIDS should talk to their healthcare provider about what vitamins and minerals they should take to improve their health.\n" +
                        "\n" +
                        "Supplements to avoid\n" +
                        "Certain supplements are known to cause problems with the effectiveness of HIV treatment. Four of these are garlic, St. John’s wort, echinacea, and ginseng.\n" +
                        "\n" +
                        "Garlic supplements can make certain HIV treatments much less effective. If garlic is taken with certain medications, it could result in too much or too little of the drug in the blood. This problem outweighs any possible benefits of these supplements on the immune system. That said, eating fresh garlic is not known to cause problems.\n" +
                        "St. John’s wort is a popular supplement used to treat depression. However, it can make HIV treatment less effective. People with HIV should not use this supplement.\n" +
                        "Echinacea and ginseng are purported to boost immune function. However, both can interact with certain HIV medications. It may be okay to use these supplements depending on the HIV therapy. A healthcare provider should be consulted.\n" +
                        "Supplements that may be helpful\n" +
                        "Supplements that may be useful in people with HIV include:\n" +
                        "\n" +
                        "calcium and vitamin D to improve bone health\n" +
                        "fish oil to reduce cholesterol\n" +
                        "selenium to slow the progression of HIV\n" +
                        "vitamin B-12 to improve the health of pregnant women and their pregnancies\n" +
                        "whey or soy protein to help with weight gain");
                break;
            case "Diabetes":
                abouttreatment.setText("1. Apple Cider Vinegar\n" +
                        "The primary compound in ACV is acetic acid and is believed to be responsible for many of its health benefits.   There are many evidence-based approaches to using ACV.  Taking 2 tablespoons before bedtime can reduce your morning fasting sugar levels.   Even better, 1-2 tablespoons of ACV taken with meals can decrease the glycemic load of a carbohydrate rich meal.   I generally tell patients to either consume ACV alone, prior to a meal or mix it into salad dressings or teas.  \n" +
                        "\n" +
                        "2. Fiber and Barley\n" +
                        "Eating fiber decreases blood sugar and insulin concentrations.   The recommended amount of fiber is around 30 grams per day.  Most Americans get around 6-8 grams, which is not nearly enough.   While you can take fiber supplements like Metamucil (psylium husk), the best way to reach your goal is to eat your veggies!  Barley is a high-fiber, high-protein grain which has lots of data to support its role in helping improve blood sugar, insulin, cholesterol and general inflammation.  Barley does not requiring soaking and usually can cook in less than 15 minutes on the stove top with just some water and salt. \n" +
                        "\n" +
                        "3. Chromium\n" +
                        "Mainly found in brewer’s yeast, deficiency in chromium impairs the metabolism of glucose.  Evidence supports chromium for lower blood sugar and A1c levels.  Be careful if you have kidney disease with this supplement. \n" +
                        "\n" +
                        "4. Zinc\n" +
                        "Those with diabetes are commonly found to be zinc deficient.  Studies have shown zinc supplementation can reduce blood sugar and A1C, have an antioxidant effect, lower blood sugar and even help treat some of the complications related to diabetes.   Large doses of zinc can inhibit the absorption of other minerals like copper, so be sure to ask for guidance of the appropriate dosing. \n" +
                        "\n" +
                        "5. Aloe Vera\n" +
                        "The sap of aloe vera is known for its laxative effect.  Therefore, make sure to get the juice of the gel! There is increasing evidence for use of the gel, which is the mucilaginous material inside the leaves.  Be sure that any product you buy is free of aloin or anthraquinones to avoid finding yourself in the bathroom!\n" +
                        "\n" +
                        "6. Berberine\n" +
                        "This is one of my all-time favorite botanicals found in plants such as goldenseal, barberry, Oregon grape root and Coptis.  Current evidence supports its use for decreasing blood sugar and hba1c. Be aware that this herb can interfere with metabolism of traditional pharmaceuticals and should never be taken while pregnant. \n" +
                        "\n" +
                        "7. Cinnamon\n" +
                        "A medically beneficial indulgence to help lower your blood sugar and cholesterol levels. \n" +
                        "\n" +
                        "8. Fenugreek\n" +
                        "A seed commonly used as a food spice has been used abroad for centuries for its medical benefits to lower cholesterol and hba1c.  If your urine smells like maple syrup, not to worry, this is a known side effect and is harmless. \n" +
                        "\n" +
                        "9. Gymnema\n" +
                        "Used for centuries in India, evidence is catching up to its medical use showing benefits for glucose metabolism, insulin levels and as an adjunct to improve the results of traditional pharmaceuticals.  Be aware, because this botanical works synergistically with your meds, you must monitor your blood sugar closely to avoid having hypoglycemia. \n" +
                        "\n" +
                        "10. Nopal\n" +
                        "Ever see nopales at your favorite Mexican restaurant?   Now you can order them and, rest assured, you’re choosing a food that help lower your blood sugar.  Nopales are the pads of the prickly pear cactus and when cooked right are delicious!  I personally like eating them with eggs or as a salad.");
                break;
            case "Gastroenteritis":
                abouttreatment.setText("Honey\n" +
                        "\n" +
                        "One should drink a cup of warm water mixed with a teaspoon of honey early in the morning to deal with diarrhoea and vomiting. Honey has antibacterial properties that not only blocks the attachment of the bacteria to the intestine but also improves the potassium absorption and keeps you hydrated.\n" +
                        "\n" +
                        "Rice water\n" +
                        "Rice water is the most effective and simple remedy for mild gastroenteritis in infants and children. Intake of rice water helps to decrease the number of stools a day. It also lowers the risk of health complications caused due to dehydration by acting as a potent rehydration fluid.\n" +
                        "\n" +
                        "Sugar and salt water"+"This is one of the most common and age-old home remedies to treat diarrhoea and dehydration. But one should get the concentration right to ensure proper electrolyte balance in the body.\n" +
                        "\n" +
                        "Curd\n" +
                        "Eat a bowl of curd daily, if you are suffering from bacterial gastroenteritis because your track needs good bacteria to heal quickly and resume normal digestion.\n" +
                        "\n" +
                        "Ginger\n" +
                        "As ginger contains anti-inflammatory properties, drinking a mild ginger tea and with a bit of honey helps to ease nausea. Honey has antibacterial properties that will help to destroy bacteria causing the gastroenteritis. You can also chew a fresh piece of ginger root to alleviate nausea.\n" +
                        "\n" +
                        "Tender coconut water\n" +
                        "Drinking a tender coconut water might help to get rid of this infection as it is filled with vital nutrients and salts that will prevent dehydration.");
                break;
            case "Bronchial Asthma":
                abouttreatment.setText("1. Dietary changes\n" +
                        "Although there’s no specific diet for people with severe asthma, there are a few steps you can take that may help with your symptoms.\n" +
                        "\n" +
                        "Being overweight can often worsen severe asthma. It’s important to maintain a healthy and balanced diet, which includes plenty of fruits and vegetables. These are good sources of antioxidants like beta-carotene and vitamins C and E, and they may help to reduce inflammation around your airways.\n" +
                        "\n" +
                        "If you experience a flare-up in asthma symptoms after eating certain foods, try to avoid eating them. It’s possible that you have a food allergy that’s causing your symptoms to worsen. Talk to your doctor to confirm this.\n" +
                        "\n" +
                        "\n" +
                        "2. Buteyko Breathing Technique\n" +
                        "The Buteyko Breathing Technique (BBT) is a system of breathing exercises. It may be able to help reduce your asthma symptoms through slow, gentle breathing.\n" +
                        "\n" +
                        "BBT focuses on breathing out of your nose instead of your mouth. Breathing out of your mouth can dry out your airways and make them more sensitive.\n" +
                        "\n" +
                        "Some people may experience less respiratory infections from using this technique. Others who practice BBT believe that it helps to raise your carbon dioxide levels. Still, there isn’t conclusive evidence to support this theory."+"3. Papworth method\n" +
                        "The Papworth method is a breathing and relaxation technique that has been used since the 1960s to help people with asthma. It involves using your nose and diaphragm to develop breathing patterns. You can then apply these breathing patterns to various activities that may cause your asthma to flare-up.\n" +
                        "\n" +
                        "A training course is usually recommended before adopting the exercises as part of your daily routine.\n" +
                        "\n" +
                        "4. Garlic\n" +
                        "Garlic has several health benefits, including anti-inflammatory properties, according to a 2013 study. Because asthma is an inflammatory disease, garlic may be able to help relieve your symptoms.\n" +
                        "\n" +
                        "Still, there’s no conclusive evidence that garlic is effective against preventing asthma flare-ups.\n" +
                        "\n" +
                        "5. Ginger\n" +
                        "Ginger is another herb that contains anti-inflammatory properties and may help with severe asthma. A 2013 study showed that oral ginger supplements were linked to an improvement in asthma symptoms. But it didn’t confirm that ginger leads to an improvement in overall lung function.\n" +
                        "\n" +
                        "\n" +
                        "6. Honey\n" +
                        "Honey is frequently used in cold remedies to help soothe the throat and reduce coughing. You can mix honey with a hot beverage like herbal tea to provide relief for your symptoms.\n" +
                        "\n" +
                        "Still, there’s limitedTrusted Source scientific evidence that honey should be used as an alternative asthma treatment.\n" +
                        "\n" +
                        "7. Omega-3 oils\n" +
                        "Omega-3 oils, which can be found in fish and flax seeds, have been shown to have many health benefits. They may also work to decrease airway inflammation and improve lung function in people with severe asthma.\n" +
                        "\n" +
                        "High doses of oral steroids, though, can block the beneficial effects of omega-3 oils. It’s a good idea to check with your doctor before upping your intake of omega-3.\n" +
                        "\n" +
                        "8. Caffeine\n" +
                        "Caffeine is a bronchodilator and can reduce respiratory muscle fatigue. A 2010 studyTrusted Source showed that caffeine can be effective for people with asthma. It may be able to improve the function of airways for up to four hours after consumption.\n" +
                        "\n" +
                        "9. Yoga\n" +
                        "Yoga incorporates stretching and breathing exercises to help boost flexibility and increase your overall fitness. For many people, practicing yoga can decrease stress, which may trigger your asthma.\n" +
                        "\n" +
                        "The breathing techniques utilized in yoga may also help to reduce the frequency of asthma attacks. However, there isn’t currently any conclusive evidence to prove this.\n" +
                        "\n" +
                        "10. Hypnotherapy\n" +
                        "In hypnotherapy, hypnosis is used to make a person more relaxed and open to new ways to think, feel, and behave. Hypnotherapy may help facilitate muscle relaxation, which may help people with asthma cope with symptoms like chest tightness.\n" +
                        "\n" +
                        "11. Mindfulness\n" +
                        "Mindfulness is a type of meditation that focuses on how the mind and the body are feeling in the present moment. It can be practiced almost anywhere. All that you need is a quiet place to sit down, close your eyes, and focus your attention on the thoughts, feelings, and sensations in your body.\n" +
                        "\n" +
                        "Because of its stress-relieving benefits, mindfulness can help to complement your prescription medication and relieve stress-related asthma symptoms.\n" +
                        "\n" +
                        "12. Acupuncture\n" +
                        "Acupuncture is a form of ancient Chinese medicine that involves placing small needles into specific points on the body. Long-term benefits of acupuncture have not yet been proven to be effective against asthma. But some people with asthma do find that acupuncture helps to improve airflow and manage symptoms like chest pain.\n" +
                        "\n" +
                        "13. Speleotherapy\n" +
                        "Speleotherapy involves spending time in a salt room to introduce tiny particles of salt into the respiratory system. There is currently no scientific evidence to prove that speleotherapy is an effective form of treatment against asthma, but one studyTrusted Source did show that it had a beneficial effect on short-term lung function.");
                break;
            case "Hypertension":
                abouttreatment.setText("1. Walk and exercise regularly\n" +
                        "Regular exercise can help lower your blood pressure.\n" +
                        "Exercise is one of the best things you can do to lower high blood pressure.\n" +
                        "\n" +
                        "Regular exercise helps make your heart stronger and more efficient at pumping blood, which lowers the pressure in your arteries.\n" +
                        "\n" +
                        "In fact, 150 minutes of moderate exercise, such as walking, or 75 minutes of vigorous exercise, such as running, per week, can help lower blood pressure and improve your heart health (3Trusted Source, 4Trusted Source).\n" +
                        "\n" +
                        "What’s more, doing even more exercise than this reduces your blood pressure even further, according to the National Walkers’ Health Study (5Trusted Source).\n" +
                        "\n" +
                        "Bottom line: Walking just 30 minutes a day can help lower your blood pressure. More exercise helps reduce it even further.\n" +
                        "\n" +
                        "2. Reduce your sodium intake\n" +
                        "Salt intake is high around the world. In large part, this is due to processed and prepared foods.\n" +
                        "\n" +
                        "For this reason, many public health efforts are aimed at lowering salt in the food industry (6Trusted Source).\n" +
                        "\n" +
                        "Many studies have linked high salt intake with high blood pressure and heart events, including stroke (7Trusted Source, 8Trusted Source).\n" +
                        "\n" +
                        "However, more recent research indicates that the relationship between sodium and high blood pressure is less clear (9Trusted Source, 10).\n" +
                        "\n" +
                        "One reason for this may be genetic differences in how people process sodium. About half of people with high blood pressure and a quarter of people with normal levels seem to have a sensitivity to salt (11Trusted Source).\n" +
                        "\n" +
                        "If you already have high blood pressure, it’s worth cutting back your sodium intake to see if it makes a difference. Swap out processed foods with fresh ones and try seasoning with herbs and spices rather than salt.\n" +
                        "\n" +
                        "Bottom line: Most guidelines for lowering blood pressure recommend reducing sodium intake. However, that recommendation might make the most sense for people who are salt-sensitive.\n" +
                        "\n" +
                        "3. Drink less alcohol\n" +
                        "Drinking alcohol can raise blood pressure. In fact, alcohol is linked to 16% of high blood pressure cases around the world (12Trusted Source).\n" +
                        "\n" +
                        "While some research has suggested that low-to-moderate amounts of alcohol may protect the heart, those benefits may be offset by adverse effects (12Trusted Source).\n" +
                        "\n" +
                        "In the U.S., moderate alcohol consumption is defined as no more than one drink a day for women and two for men. If you drink more than that, cut back.\n" +
                        "\n" +
                        "Bottom line: Drinking alcohol in any quantity may raise your blood pressure. Limit your drinking in line with the recommendations.\n" +
                        "\n" +
                        "4. Eat more potassium-rich foods\n" +
                        "Potassium is an important mineral.\n" +
                        "\n" +
                        "It helps your body get rid of sodium and eases pressure on your blood vessels.\n" +
                        "\n" +
                        "Modern diets have increased most people’s sodium intake while decreasing potassium intake (13Trusted Source).\n" +
                        "\n" +
                        "To get a better balance of potassium to sodium in your diet, focus on eating fewer processed foods and more fresh, whole foods.\n" +
                        "\n" +
                        "Foods that are particularly high in potassium include:\n" +
                        "\n" +
                        "vegetables, especially leafy greens, tomatoes, potatoes, and sweet potatoes\n" +
                        "fruit, including melons, bananas, avocados, oranges, and apricots\n" +
                        "dairy, such as milk and yogurt\n" +
                        "tuna and salmon\n" +
                        "nuts and seeds\n" +
                        "beans\n" +
                        "Bottom line: Eating fresh fruits and vegetables, which are rich in potassium, can help lower blood pressure.\n" +
                        "\n" +
                        "\n" +
                        "5. Cut back on caffeine\n" +
                        "If you’ve ever downed a cup of coffee before you’ve had your blood pressure taken, you’ll know that caffeine causes an instant boost.\n" +
                        "\n" +
                        "However, there’s not a lot of evidence to suggest that drinking caffeine regularly can cause a lasting increase (14Trusted Source).\n" +
                        "\n" +
                        "In fact, people who drink caffeinated coffee and tea tend to have a lower risk of heart disease, including high blood pressure, than those who don’t drink it (15Trusted Source, 16Trusted Source, 17Trusted Source, 18Trusted Source).\n" +
                        "\n" +
                        "Caffeine may have a stronger effect on people who don’t consume it regularly (19).\n" +
                        "\n" +
                        "If you suspect you’re caffeine-sensitive, cut back to see if it lowers your blood pressure (20Trusted Source).\n" +
                        "\n" +
                        "Bottom line: Caffeine can cause a short-term spike in blood pressure, although for many people, it does not cause a lasting increase."+"6. Learn to manage stress\n" +
                        "Listening to soothing music may help lower stress.\n" +
                        "Stress is a key driver of high blood pressure.\n" +
                        "\n" +
                        "When you’re chronically stressed, your body is in a constant fight-or-flight mode. On a physical level, that means a faster heart rate and constricted blood vessels.\n" +
                        "\n" +
                        "When you experience stress, you might also be more likely to engage in other behaviors, such as drinking alcohol or eating unhealthful food that can adversely affect blood pressure.\n" +
                        "\n" +
                        "Several studies have explored how reducing stress can help lower blood pressure. Here are two evidence-based tips to try:\n" +
                        "\n" +
                        "Listen to soothing music: Calming music can help relax your nervous system. Research has shown it’s an effective complement to other blood pressure therapies (21Trusted Source, 22Trusted Source).\n" +
                        "Work less: Working a lot, and stressful work situations, in general, are linked to high blood pressure (23Trusted Source, 24Trusted Source).\n" +
                        "Bottom line: Chronic stress can contribute to high blood pressure. Finding ways to manage stress can help.\n" +
                        "\n" +
                        "7. Eat dark chocolate or cocoa\n" +
                        "Here’s a piece of advice you can really get behind.\n" +
                        "\n" +
                        "While eating massive amounts of dark chocolate probably won’t help your heart, small amounts may.\n" +
                        "\n" +
                        "That’s because dark chocolate and cocoa powder are rich in flavonoids, which are plant compounds that cause blood vessels to dilate (25Trusted Source).\n" +
                        "\n" +
                        "A review of studies found that flavonoid-rich cocoa improved several markers of heart health over the short term, including lowering blood pressure (25Trusted Source).\n" +
                        "\n" +
                        "For the strongest effects, use non-alkalized cocoa powder, which is especially high in flavonoids and has no added sugars.\n" +
                        "\n" +
                        "Bottom line: Dark chocolate and cocoa powder contain plant compounds that help relax blood vessels, lowering blood pressure.\n" +
                        "\n" +
                        "8. Lose weight\n" +
                        "In people with overweight, losing weight can make a big difference to heart health.\n" +
                        "\n" +
                        "According to a 2016 study, losing 5% of your body mass could significantly lower high blood pressure (26Trusted Source).\n" +
                        "\n" +
                        "In previous studies, losing 17.64 pounds (8 kilograms) was linked to lowering systolic blood pressure by 8.5 mm Hg and diastolic blood pressure by 6.5 mm Hg (27Trusted Source).\n" +
                        "\n" +
                        "To put that in perspective, a healthy reading should be less than 120/80 mm Hg (4Trusted Source).\n" +
                        "\n" +
                        "The effect is even greater when weight loss is paired with exercise (27Trusted Source).\n" +
                        "\n" +
                        "Losing weight can help your blood vessels do a better job of expanding and contracting, making it easier for the left ventricle of the heart to pump blood.\n" +
                        "\n" +
                        "Bottom line: Losing weight can significantly lower high blood pressure. This effect is even more significant when you exercise.\n" +
                        "\n" +
                        "9. Quit smoking\n" +
                        "Among the many reasons to quit smoking is that the habit is a strong risk factor for heart disease.\n" +
                        "\n" +
                        "Every puff of cigarette smoke causes a slight, temporary increase in blood pressure. The chemicals in tobacco are also known to damage blood vessels.\n" +
                        "\n" +
                        "Surprisingly, studies haven’t found a conclusive link between smoking and high blood pressure. Perhaps this is because smokers develop a tolerance over time (28Trusted Source).\n" +
                        "\n" +
                        "Still, since both smoking and high blood pressure raise the risk of heart disease, quitting smoking can help lessen that risk.\n" +
                        "\n" +
                        "Bottom line: There’s conflicting research about smoking and high blood pressure, but what is clear is that both increase the risk of heart disease.\n" +
                        "\n" +
                        "10. Cut added sugar and refined carbs\n" +
                        "There’s a growing body of research showing a link between added sugar and high blood pressure (29Trusted Source, 30, 31).\n" +
                        "\n" +
                        "In the Framingham Women’s Health Study, women who drank even one soda per day had higher levels than those who drank less than one soda per day (32Trusted Source).\n" +
                        "\n" +
                        "Another study found that having one less sugar-sweetened beverage per day was linked to lower blood pressure (33Trusted Source).\n" +
                        "\n" +
                        "And it’s not just sugar — all refined carbs, such as the kind found in white flour — convert rapidly to sugar in your bloodstream and may cause problems.\n" +
                        "\n" +
                        "Some studies have shown that low carb diets may also help reduce blood pressure.\n" +
                        "\n" +
                        "One study on people undergoing statin therapy found that those who went on a 6-week, carb-restricted diet saw a greater improvement in blood pressure and other heart disease markers than people who did not restrict carbs (34Trusted Source).\n" +
                        "\n" +
                        "Bottom line: Refined carbs, especially sugar, may raise blood pressure. Some studies have shown that low carb diets may help reduce your levels.\n" +
                        "\n" +
                        "11. Eat berries\n" +
                        "Berries are full of more than just juicy flavor.\n" +
                        "\n" +
                        "They’re also packed with polyphenols, natural plant compounds that are good for your heart.\n" +
                        "\n" +
                        "Polyphenols can reduce the risk of stroke, heart conditions, and diabetes, as well as improving blood pressure, insulin resistance, and systemic inflammation (34).\n" +
                        "\n" +
                        "One study assigned people with high blood pressure to a low-polyphenol diet or a high-polyphenol diet containing berries, chocolate, fruits, and vegetables (35).\n" +
                        "\n" +
                        "Those consuming berries and polyphenol-rich foods experienced improved markers of heart disease risk.\n" +
                        "\n" +
                        "Bottom line: Berries are rich in polyphenols, which can help lower blood pressure and the overall risk of heart disease.\n" +
                        "\n" +
                        "12. Try meditation or deep breathing\n" +
                        "While these two behaviors could also fall under “stress reduction techniques,” meditation and deep breathing deserve specific mention.\n" +
                        "\n" +
                        "Both meditation and deep breathing may activate the parasympathetic nervous system. This system is engaged when the body relaxes, slowing the heart rate, and lowering blood pressure.\n" +
                        "\n" +
                        "There’s quite a bit of research in this area, with studies showing that different styles of meditation appear to have benefits for lowering blood pressure (36Trusted Source, 37Trusted Source).\n" +
                        "\n" +
                        "Deep breathing techniques can also be quite effective.\n" +
                        "\n" +
                        "In one study, participants were asked to either take six deep breaths over the course of 30 seconds or simply sit still for 30 seconds. Those who took breaths lowered their blood pressure more than those who just sat (38Trusted Source).\n" +
                        "\n" +
                        "Try guided meditation or deep breathing. Here’s a video to get you started.\n" +
                        "\n" +
                        "Bottom line: Both meditation and deep breathing can activate the parasympathetic nervous system, which helps slow your heart rate and lower blood pressure.\n" +
                        "\n" +
                        "13. Eat calcium-rich foods\n" +
                        "People with low calcium intake often have high blood pressure.\n" +
                        "\n" +
                        "While calcium supplements haven’t been conclusively shown to lower blood pressure, calcium-rich diets do seem linked to healthful levels (39Trusted Source, 40Trusted Source).\n" +
                        "\n" +
                        "For most adults, the calcium recommendation is 1,000 milligrams (mg) per day. For women over 50 and men over 70, it’s 1,200 mg per day (41).\n" +
                        "\n" +
                        "In addition to dairy, you can get calcium from collard greens and other leafy greens, beans, sardines, and tofu. Here is a list of calcium-rich plant-based foods.\n" +
                        "\n" +
                        "Bottom line: Calcium-rich diets are linked to healthy blood pressure levels. You can get calcium through eating dark leafy greens and tofu, as well as dairy.\n" +
                        "\n" +
                        "14. Take natural supplements\n" +
                        "Some natural supplements may also help lower blood pressure. Here are some of the main supplements that have evidence behind them:\n" +
                        "\n" +
                        "Aged garlic extract: Researchers have used aged garlic extract successfully as a stand-alone treatment and along with conventional therapies for lowering blood pressure (42Trusted Source, 43Trusted Source).\n" +
                        "Berberine: Traditionally used in Ayurvedic and Chinese medicine, berberine may increase nitric oxide production, which helps decrease blood pressure (44Trusted Source, 45Trusted Source).\n" +
                        "Whey protein: A 2016 study found that whey protein improved blood pressure and blood vessel function in 38 participants (46Trusted Source).\n" +
                        "Fish oil: Long credited with improving heart health, fish oil may benefit people with high blood pressure the most (47Trusted Source, 48).\n" +
                        "Hibiscus: Hibiscus flowers make a tasty tea. They’re rich in anthocyanins and polyphenols that are good for your heart and may lower blood pressure (49Trusted Source).\n" +
                        "Read more about supplements for high blood pressure here.\n" +
                        "\n" +
                        "Bottom line: Researchers have investigated several natural supplements for their ability to lower blood pressure.\n" +
                        "\n" +
                        "15. Eat foods rich in magnesium\n" +
                        "Magnesium is an important mineral that helps blood vessels relax.\n" +
                        "\n" +
                        "While magnesium deficiency is pretty rare, many people don’t get enough.\n" +
                        "\n" +
                        "Some studies have suggested that getting too little magnesium is linked with high blood pressure, but evidence from clinical studies has been less clear (50Trusted Source, 51Trusted Source).\n" +
                        "\n" +
                        "Still, eating a magnesium-rich diet is a recommended way to ward off high blood pressure (51Trusted Source).\n" +
                        "\n" +
                        "You can incorporate magnesium into your diet by consuming vegetables, dairy products, legumes, chicken, meat, and whole grains.\n" +
                        "\n" +
                        "Bottom line: Magnesium is an essential mineral that helps regulate blood pressure. Find it in whole foods, such as legumes and whole grains.");
                break;
            case "Migraine":
                abouttreatment.setText("1. Avoid certain foods\n" +
                        "Diet plays a vital role in preventing migraine attacks. Many foods and beverages may be migraine triggers, such as:\n" +
                        "\n" +
                        "foods with nitrates, including hot dogs, deli meats, bacon, and sausage\n" +
                        "chocolate\n" +
                        "cheese that contains the naturally occurring compound tyramine, such as blue, feta, cheddar, Parmesan, and Swiss\n" +
                        "alcohol, especially red wine\n" +
                        "foods that contain monosodium glutamate (MSG), a flavor enhancer\n" +
                        "foods that are very cold, such as ice cream or iced drinks\n" +
                        "processed foods\n" +
                        "pickled foods\n" +
                        "beans\n" +
                        "dried fruits\n" +
                        "cultured dairy products, such as buttermilk, sour cream, and yogurt\n" +
                        "A small amount of caffeine may ease migraine pain in some people. Caffeine is also in some migraine medications. But too much caffeine may cause a migraine attack. It may also lead to a severe caffeine withdrawal headache.\n" +
                        "\n" +
                        "To figure out which foods and beverages trigger migraine attacks for you, keep a daily food journal. Record everything you eat and note how you feel afterward.\n" +
                        "\n" +
                        "\n" +
                        "2. Apply lavender oil\n" +
                        "Inhaling lavender essential oil may ease migraine pain. Lavender oil may be inhaled directly or diluted with a carrier oil and applied in small amounts to your temples.\n" +
                        "\n" +
                        "A 2016 randomized controlled study found evidence that 3 months of lavender therapy as a prophylactic therapy, meaning taken before a migraine attack begins, reduced frequency and severity of migraine attacks. However, research is still limited.\n" +
                        "\n" +
                        "A 2020 review of studiesTrusted Source published in the journal Phytotherapy Research examined the ability of various herbal treatments, including lavender therapy for migraine. The authors found mixed or limited evidence to support the use of butterbur and feverfew for treating migraine but didn’t note that current research supports the use of lavender.\n" +
                        "\n" +
                        "According to the authors, many studies had a high risk for bias, and more high quality research is needed."+"3. Try acupuncture\n" +
                        "Acupuncture involves injecting very thin needles into certain parts of your skin to stimulate relief from a wide variety of health conditions.\n" +
                        "\n" +
                        "A 2020 randomized controlled studyTrusted Source found that 20 sessions of manual acupuncture along with usual care was more effective at preventing migraine in people with a history of episodic migraine without aura than sham acupuncture along with usual care. Sham acupuncture is a treatment where the needles are not inserted as deeply.\n" +
                        "\n" +
                        "A 2016 review of 22 studiesTrusted Source also found moderate evidence that acupuncture may reduce headache symptoms. In the results summary, the authors explain that if people had 6 days of migraine per month before treatment, it would be expected that they would have:\n" +
                        "\n" +
                        "5 days with usual care\n" +
                        "4 days with fake acupuncture or prophylactic medications\n" +
                        "3 1/2 days with real acupuncture\n" +
                        "4. Look for feverfew\n" +
                        "Feverfew is a flowering herb that looks like a daisy. It’s a folk remedy for migraine. It still isn’t well-studied, but there is some evidence that it may be slightly more effective than a placebo for treating migraine.\n" +
                        "\n" +
                        "In a 2015 review of studiesTrusted Source, which is an update of a previous 2004 study, the authors concluded that larger studies are needed to support the use of feverfew for treating migraine.\n" +
                        "\n" +
                        "The authors note that one larger study published since the 2004 review found 0.6 fewer migraine days per month in people who took feverfew versus a placebo. They describe previous studies as low quality or providing mixed evidence.\n" +
                        "\n" +
                        "The 2020 review of studiesTrusted Source published in Phytotherapy Researchalso summarizes the finding on feverfew as “mixed.”\n" +
                        "\n" +
                        "5. Apply peppermint oil\n" +
                        "The chemical menthol found in peppermint oil may help prevent migraine episodes, although there’s a very limited amount of research.\n" +
                        "\n" +
                        "A 2019 randomized controlled studyTrusted Source compared the effects of nasal 4 percent lidocaine with 1.5 percent peppermint essential oil and a placebo for managing migraine symptoms.\n" +
                        "\n" +
                        "The researchers found that 40 percent of people in the lidocaine and peppermint oil groups experienced considerable improvements in their symptoms, compared with only 4.9 percent of people in the placebo group.\n" +
                        "\n" +
                        "The National Center for Complementary and Integrative HealthTrusted Source notes that very little research has examined peppermint leaf, but a limited amount of evidence suggests topical peppermint oil may benefit tension headaches.\n" +
                        "\n" +
                        "\n" +
                        "6. Ginger\n" +
                        "Ginger is known to ease nausea caused by many conditions, including migraine. It may have pain-relieving benefits for migraine attacks. According to a 2020 review of studiesTrusted Source, one randomized controlled study found evidence that ginger may have beneficial activity.\n" +
                        "\n" +
                        "More research is needed to understand the extent and usefulness of ginger for treating migraine-related pain.\n" +
                        "\n" +
                        "BEZZY COMMUNITY\n" +
                        "Get Real Answers About Managing Migraine\n" +
                        "Tap into powerful articles on migraine relief backed by a community that understands. Bezzy Migraine: empowered by each other.\n" +
                        "\n" +
                        "7. Sign up for yoga\n" +
                        "Yoga uses breathing, meditation, and body postures to promote health and well-being. A 2015 studyTrusted Source found yoga may relieve the frequency, duration, and intensity of migraine attacks. It’s thought to improve anxiety, release tension in migraine-trigger areas, and improve vascular health.\n" +
                        "\n" +
                        "The researchers concluded that yoga could be beneficial as a complementary therapy for treating migraine.\n" +
                        "\n" +
                        "8. Try biofeedback\n" +
                        "Biofeedback is a relaxation method. It teaches you to control autonomic reactions to stress. During this therapy, electrodes are applied to your skin to monitor physiologic processes that change with stress, such as your heart rate, blood pressure, and muscle tension.\n" +
                        "\n" +
                        "During a biofeedback session, you work with a therapist to manage stress using changes in your physiologic processes as feedback.\n" +
                        "\n" +
                        "According to a 2019 studyTrusted Source, there’s good evidence to support the use of mind-body interventions such as biofeedback and cognitive behavioral therapy for treating migraine. These therapies are effectively free of side effects and may make a good alternative for medication for some people.\n" +
                        "\n" +
                        "9. Add magnesium to your diet\n" +
                        "Magnesium deficiency is linked to headaches and migraine. Magnesium oxide supplementation may help prevent migraine with aura. It may also prevent menstrual migraine (hormone headaches).\n" +
                        "\n" +
                        "A 2021 studyTrusted Source found that 500 milligrams of magnesium oxide taken twice a day for 8 weeks was as effective as the medication valproate sodium for preventing migraine without significant side effects.\n" +
                        "\n" +
                        "You can get magnesium from foods that include:\n" +
                        "\n" +
                        "almonds\n" +
                        "sesame seeds\n" +
                        "sunflower seeds\n" +
                        "Brazil nuts\n" +
                        "cashews\n" +
                        "peanut butter\n" +
                        "oatmeal\n" +
                        "eggs\n" +
                        "milk\n" +
                        "10. Book a massage\n" +
                        "Massage may reduce migraine frequency. Migraine is associated with low serotonin in the brain, and massage has been shown to increase serotonin. There’s limited evidence to support the use of massage for migraine relief, but it’s generally safe and has a low risk of side effects.");
                break;
            case "Cervical spondylosis":
                abouttreatment.setText("When to see a doctor\n" +
                        "If you have the sudden onset of numbness or tingling in the shoulder, arms, or legs, or if you lose bowel or bladder control, talk to your doctor and seek medical attention as soon as possible. This is a medical emergency.\n" +
                        "\n" +
                        "If your pain and discomfort start to interfere with your daily activities, you may wish to make an appointment with your doctor. If you don’t already have a provider, our Healthline FindCare tool can help you connect to physicians in your area.\n" +
                        "\n" +
                        "Although the condition is often the result of aging, there are treatments available that can reduce pain and stiffness."+"Testing for and diagnosing the condition\n" +
                        "Making a diagnosis of cervical spondylosis involves ruling out other potential conditions, such as fibromyalgia. Making a diagnosis also involves testing for movement and determining the affected nerves, bones, and muscles.\n" +
                        "\n" +
                        "Your doctor may treat your condition or refer you to an orthopedic specialist, neurologist, or neurosurgeon for further testing.\n" +
                        "\n" +
                        "Physical exam\n" +
                        "Your doctor will start by asking you several questions regarding your symptoms. Then, they’ll run through a set of tests.\n" +
                        "\n" +
                        "Typical exams include testing your reflexes, checking for muscle weakness or sensory deficits, and testing the range of motion of your neck.\n" +
                        "\n" +
                        "Your doctor might also want to watch how you walk. All of this helps your doctor determine if your nerves and spinal cord are under too much pressure.\n" +
                        "\n" +
                        "If your doctor suspects cervical spondylosis, they’ll then order imaging tests and nerve function tests to confirm the diagnosis.\n" +
                        "\n" +
                        "Imaging tests\n" +
                        "X-rays can be used to check for bone spurs and other abnormalities.\n" +
                        "A CT scan can provide more detailed images of your neck.\n" +
                        "An MRI scan, which produces images using radio waves and a magnetic field, helps your doctor locate pinched nerves.\n" +
                        "In a myelogram, a dye injection is used to highlight certain areas of your spine. CT scans or X-rays are then used to provide more detailed images of these areas.\n" +
                        "An electromyogram (EMG) is used to check that your nerves are functioning normally when sending signals to your muscles. This test measures your nerves’ electrical activity.\n" +
                        "A nerve conduction study checks the speed and strength of the signals a nerve sends. This is done by placing electrodes on your skin where the nerve is located.\n" +
                        "Treating cervical spondylosis\n" +
                        "Treatments for cervical spondylosis focus on providing pain relief, lowering the risk of permanent damage, and helping you lead a normal life.\n" +
                        "\n" +
                        "Nonsurgical methods are usually very effective.\n" +
                        "\n" +
                        "Physical therapy\n" +
                        "Your doctor might send you to a physical therapist for treatment. Physical therapy helps you stretch your neck and shoulder muscles. This makes them stronger and ultimately helps to relieve pain.\n" +
                        "\n" +
                        "You might also have neck traction. This involves using weights to increase the space between the cervical joints and relieve the pressure on the cervical discs and nerve roots.\n" +
                        "\n" +
                        "Medications\n" +
                        "Your doctor might prescribe certain medications if over-the-counter (OTC) drugs don’t work. These include:\n" +
                        "\n" +
                        "muscle relaxants, such as cyclobenzaprine (Fexmid), to treat muscle spasms\n" +
                        "narcotics, such as hydrocodone (Norco), for pain relief\n" +
                        "anti-epileptic drugs, such as gabapentin (Neurontin), to relieve pain caused by nerve damage\n" +
                        "steroid injections, such as prednisone, to reduce tissue inflammation and subsequently lessen pain\n" +
                        "prescription nonsteroidal anti-inflammatory drugs (NSAIDs), such as diclofenac (Voltaren-XR), to reduce inflammation\n" +
                        "Surgery\n" +
                        "If your condition is severe and doesn’t respond to other forms of treatment, you might need surgery. This can involve removing bone spurs, parts of your neck bones, or herniated discs to give your spinal cord and nerves more room.\n" +
                        "\n" +
                        "Surgery is rarely necessary for cervical spondylosis. However, a doctor may recommend it if the pain is severe and it’s affecting your ability to move your arms.\n" +
                        "\n" +
                        "Home treatment options\n" +
                        "If your condition is mild, you can try a few things at home to treat it:\n" +
                        "\n" +
                        "Take an OTC pain reliever, such as acetaminophen (Tylenol) or an NSAID, which includes ibuprofen (Advil) and naproxen sodium (Aleve).\n" +
                        "Use a heating pad or a cold pack on your neck to provide pain relief for sore muscles.\n" +
                        "Exercise regularly to help you recover faster.\n" +
                        "Wear a soft neck brace or soft collar to get temporary relief. However, you shouldn’t wear a neck brace or collar for long periods of time because that can make your muscles weaker.");
                break;
            case "Paralysis (brain hemorrhage)":
                abouttreatment.setText("Dietary Tips For Paralysis:\n" +
                        "Always eat fresh food that is warm. Avoid cold food.\n" +
                        "Avoid foods that are bitter, acidic, or pungent.\n" +
                        "Include foods that are sweet, sour, and salty.\n" +
                        "Nuts are very good options to include in your daily diet.\n" +
                        "While it is ok to consume rice and wheat in your diet, avoid barley, millet, and rye.\n" +
                        "While having meat, choose white meat and avoid red meats.\n" +
                        "Consume sizable portions of carrot, beetroot, okra, and asparagus as a part of your daily diet.\n" +
                        "Home Remedies For Paralysis:\n" +
                        "Clean and grind asparagus (genus) leaves and apply it on the area of pain caused by paralysis.\n" +
                        "For relief from Inflammation and pain due to it, Saute a few drumstick leaves in castor oil and apply on pain area.\n" +
                        "Radish oil 20-40 ml twice a day daily can help in curing the condition.\n" +
                        "If you have a distorted face as a result of paralysis, mix equal portions of black pepper powder and dry ginger powder with honey and consume it twice or thrice a day.\n" +
                        "Grind the akarkara root into a fine paste and mix it with mahua (honey tree/butter tree) oil. Massage the affected area twice or thrice daily.\n" +
                        "This helps in reducing muscle tension and also helps regaining its tone. In fact, a whole body massage with this oil can help in regaining muscle strength faster.\n" +
                        "Take 1 kg sugarcane root and grind it coarsely. Boil it in water and reduce it to half the quantity. Strain the mixture. Mix it with 6 gm each of piple bamsalochan, black pepper, cardamom, and mulethi (licorice) and 1/2 kg of Misri (crystallized sugar lumps). Simmer it and bring it to a syrup consistency. Take 1-2 gm regularly. This also helps in paralysis.\n" +
                        "Bromelain is an enzyme found in pineapple juice and stem that helps in reducing pain. Take 1500 mg of bromelain tablet thrice a day.\n" +
                        "Take 180 to 240 gm of gingko a day. This helps in prevention of another stroke attack.\n" +
                        "Flaxseed oil 1 teaspoon a day also helps.\n" +
                        "Vitamin C 3000 mg a day also helps.");
                break;
            case "Jaundice":
                abouttreatment.setText("1. Natural Sunlight\n" +
                        "When infants are diagnosed with Jaundice, apart from phototherapy, doctors recommend exposing them to sunlight as it is beneficial.\n" +
                        "\n" +
                        "2. Sugarcane Juice\n" +
                        "Sugarcane juice helps in strengthening the liver and aids in its proper functioning. Have a glass daily till your jaundice improves.\n" +
                        "\n" +
                        "3. Goat’s Milk\n" +
                        "At par with cow’s milk regarding nutrients, goat’s milk is easy to digest and is suitable for infants and adults alike. Goat’s milk also has useful antibodies that help in curing jaundice.\n" +
                        "\n" +
                        "4. Green Grapes’ Juice\n" +
                        "Juice of green grapes helps in improving the liver functioning. It balances the serum bilirubin levels and cures jaundice.\n" +
                        "\n" +
                        "5. Ginger\n" +
                        "Ginger has excellent antioxidative properties. It is also hypolipidemic so it helps the liver. Have in the form of ginger tea for best results.\n" +
                        "\n" +
                        "6. Garlic\n" +
                        "Garlic is a powerful antioxidant. This helps with liver detoxification and thus contributes to curing jaundice. Know more about the health benefits of garlic.\n" +
                        "\n" +
                        "7. Lemon\n" +
                        "Lemon juice helps in unblocking the bile ducts as it has antioxidant properties. Also, it enhances immunity and stops further damage to the liver.\n" +
                        "\n" +
                        "8. Yogurt\n" +
                        "The probiotics in yogurt help improve immunity. It brings down levels of serum bilirubin and offers protection against harmful bacteria. Have a bowl of curd daily to cure your jaundice. Read more on the health benefits of yogurt.\n" +
                        "\n" +
                        "9. Tomatoes\n" +
                        "Lycopene, a compound found in tomatoes, is a potent antioxidant. This helps with detoxification of the liver and curing of jaundice. Make a juice of boiled tomatoes and have a glass daily.\n" +
                        "\n" +
                        "10. Amla\n" +
                        "Amla is rich in vitamin C and other essential nutrients that help in combating jaundice. It improves liver functioning and helps balance the serum bilirubin levels. Boil amlas, make a paste and mix this with water and honey and have daily.");
                break;
            case "Malaria":
                abouttreatment.setText("1. Citrus Fruits\n" +
                        "These are the known immune-boosters. Available in all tropical regions, citrus fruits are not hard to get hold off. Vitamin C helps in controlling fever, halts the infection from spreading and accelerates healing in the body. Lemon water, orange and sweet lime are good options. Eat as fruits or have as juice depending upon your condition.\n" +
                        "\n" +
                        "2. Cinnamon and Honey\n" +
                        "Fever, headache and diarrhoea are the most common malaria symptoms. Cinnamon has an anti-inflammatory nature. It reduces the pain and other symptoms present in malaria. It reduces the pain and other symptoms present in malaria. You can boil a mixture of one teaspoon cinnamon powder and honey, with a little bit of pepper powder in water. Drinking this once or twice daily is considered a valuable remedy for treating malaria.\n" +
                        "\n" +
                        "3. Ginger\n" +
                        "Ginger is also a useful antimalaria medicine. It can be boiled with water and reduced into a delicious concoction that will effectively help you with the recovery process. The antibacterial nature of ginger ensures that the disease does not increase further.\n" +
                        "\n" +
                        "4. Grapefruit\n" +
                        "This is the elixir of life when it comes to malaria disease. Grapefruit has a component similar to quinine and helps in neutralising the harmful effects of the malaria parasite in the body. Eating grapefruit regularly helps in combating this disease and recovering rapidly. Please note that in case you are taking quinine medicines, do not have grapefruit as it reduces the stomach’s capacity to absorb this medicine.\n" +
                        "\n" +
                        "5. Holy Basil (Tulsi)\n" +
                        "Holy Basil, is popularly known as Tulsi, it is antibacterial and has Eugenol, an active component that is hugely therapeutic and eliminates the infection. Holy Basil is found extensively in tropical countries and is used in ayurvedic medicines. It can alleviate joint pain, inflammation and other common symptoms of malaria. You can drink the water boiled with Tulsi leaves in it and can also add it to your tea. Read more on the health benefits of tulsi (basil)\n" +
                        "\n" +
                        "6. Avoiding Mosquitoes\n" +
                        "Using mosquito nets and mosquito repellents help in preventing mosquitoes in the first place, so you don’t catch the dreaded malaria disease. Ensuring that there are no stagnant pools of waters and there are no open drains also provides that mosquitoes don’t get breeding grounds.\n" +
                        "\n" +
                        "7. Apple Cider Vinegar\n" +
                        "Using a cloth dipped in a concoction of apple cider vinegar and water has been known to reduce fever during malaria infection. Add a spoonful of apple cider vinegar in a glass of water. Drinking this helps with dealing with nausea and vomiting. Also, read 6 health benefits of apple cider vinegar\n" +
                        "\n" +
                        "8. Fever Nut\n" +
                        "As the name suggests, fever nut helps in reducing body temperature. The seeds are individually considered effective against malaria symptoms and make for a great option as home remedies for malaria. The seeds of fever nut can be ingested with water.\n" +
                        "\n" +
                        "9. Herbal Tea and Milk\n" +
                        "Having 2 cups of herbal tea or turmeric milk every day is an effective treatment for malaria. Herbal tea is hygenic and should be made using boiling water. The dried leaves or stems must be boiled properly, filtered and then you can add milk to it, stir well and drink. With regular consumption, you will notice positive results. \n" +
                        "\n" +
                        "10. Turmeric\n" +
                        "We are all aware of the numerous benefits of turmeric. It is a super spice with antioxidant and antimicrobial properties. It flushes out harmful toxins from the body and helps in killing malaria parasites. The anti-inflammatory properties of turmeric help in reducing muscle and joint pain, which are common symptoms of malaria. You can drink a glass of turmeric milk every night to quickly recover from malaria.");
                break;
            case "Chicken pox":
                abouttreatment.setText("1. Apply calamine lotion\n" +
                        "Calamine lotion can help reduce itching. This lotion contains skin-soothing properties, including zinc oxide.\n" +
                        "\n" +
                        "Using a clean finger or cotton swab, dab or spread calamine lotion on itchy skin areas. Note that you shouldn’t use calamine lotion on or around chickenpox on your eyes.\n" +
                        "\n" +
                        "\n" +
                        "2. Serve sugar-free popsicles\n" +
                        "Chickenpox can also appear inside your mouth. This can be especially painful.\n" +
                        "\n" +
                        "Encouraging a child to suck on sugar-free popsicles can be a good way to soothe mouth sores. As a bonus, this allows your child to get more fluids and avoid dehydration.\n" +
                        "\n" +
                        "3. Bathe in oatmeal\n" +
                        "Oatmeal baths can be soothing and itch-relieving for chickenpox. Taking a bath won’t spread the chickenpox from one area of your skin to another.\n" +
                        "\n" +
                        "While you can purchase oatmeal bath products at most drugstores, you can also make your own oatmeal bath using the following steps:\n" +
                        "\n" +
                        "Use one cup of oatmeal for an older child or 1/3 cup for a baby or small child. The oatmeal can be unflavored instant, slow-cooked oats, or quick oats. You can use a food processor or coffee grinder to make the oatmeal flakes very small. Placing oatmeal in a muslin bag or pantyhose can also work.\n" +
                        "Draw a bath of warm (not hot) water. Place one tablespoon of ground oatmeal into a glass of warm water. If the oats appear to be absorbing water and turning the water a milky shade, the oatmeal is finely ground enough.\n" +
                        "Place the oatmeal or bag of oats into the bath. Soak for no more than 20 minutes.\n" +
                        "You may also apply oatmeal lotions to skin. This can have a soothing and moisturizing effect on itchy chickenpox blisters.\n" +
                        "\n" +
                        "4. Wear mittens to prevent scratching\n" +
                        "Scratching your blisters may be tempting, but it can worsen your discomfort and expose your skin to infection.\n" +
                        "\n" +
                        "To prevent the temptation to scratch at night or during naptime, put mittens or soft socks over your child’s hands. Trimming your child’s fingernails so they won’t damage affected areas can also help.\n" +
                        "\n" +
                        "5. Take baking soda baths\n" +
                        "Another itch-relieving option to add to a bath is baking soda. Add one cup of baking soda to a shallow, lukewarm bath. Soak for 15 to 20 minutes. Your child can take up to three baths a day if they find this approach soothing.\n" +
                        "\n" +
                        "\n" +
                        "6. Use chamomile compresses\n" +
                        "The chamomile tea in your kitchen cabinet may also soothe itchy chickenpox areas. Chamomile has antiseptic and anti-inflammatory effectsTrusted Source when applied to your skin.\n" +
                        "\n" +
                        "Brew two to three chamomile tea bags and allow to cool or place in a warm bath. Then, dip soft cotton pads or washcloths in the tea and apply to itchy areas of skin. When you are done applying compresses, pat skin gently to dry.\n" +
                        "\n" +
                        "7. Give approved pain relievers\n" +
                        "If your child’s chickenpox blisters are especially painful or if you child has a fever, you may wish to give them medication.\n" +
                        "\n" +
                        "It’s important not to give a child or teenager aspirin, as they are at increased risk for a condition called Reye’s syndrome if they take aspirin during or when they’re recovering from an infection like chickenpox. Instead, medication like acetaminophen (Tylenol) can help to relieve painful symptoms. Avoid ibuprofen if possible, because using it during a chickenpox infection may be associated with a higher risk of a severe skin infectionTrusted Source.\n" +
                        "\n" +
                        "When should you see a doctor?\n" +
                        "While most cases of chickenpox will go away with time, there are some instances where you should call your doctor or pediatrician. These include:\n" +
                        "\n" +
                        "if your child is under age 1 and has the virus\n" +
                        "if your child has a history of a weak immune system or is immunocompromised due to chronic illness or cancer\n" +
                        "if your child has a fever greater than 102°F (39°C) or if their fever lasts longer than four days or goes away for over 24 hours and then comes back\n" +
                        "if your child has a stiff neck, confusion, problems breathing, or a rash that’s bleeding");
                break;
            case "Dengue":
                abouttreatment.setText("1. Giloy juice\n" +
                        "Giloy juice is a well-known remedy for dengue fever. Giloy juice improves metabolism and builds immunity. Strong immunity helps in fighting dengue fever effectively. It helps in increasing the platelet count and gives relief to the patient. You can boil two small stems of giloy plant in a glass of water. Consume this water when it is little warm. You can also add few drops of giloy juice to a cup of boiled water and drink this twice a day. But make sure that you do not over consume giloy juice."+"\n 2. Papaya leaf juice\n" +
                        "As platelet count comes down in dengue patients, papaya leaf juice is a great remedy to increase platelet count. Papaya leaf juice also improves immunity which also helps in treating dengue. To use papaya leaves for dengue, take some papaya leaves and crush them to extract juice out of it. You can consume a small quantity of papaya leaf juice twice a day for better results."+"\n 3. Fresh guava juice\n" +
                        "Guava juice is loaded with multiple nutrients. It is rich in vitamin C which helps in building immunity. You can add fresh guava juice to your diet to treat dengue fever. Guava juice will also provide you other health benefits. Drink one cup of guava juice twice a day. You can also eat fresh guava instead of juice."+"\n 4. Fenugreek seeds\n" +
                        "Fenugreek seeds are also rich in multiple nutrients which help in controlling dengue fever. You can soak some fenugreek seeds in a cup of hot water. Allow the water to cool down and drink it twice a day. Fenugreek water will also provide you other health benefits as it is rich in vitamin C, K and fibre. Fenugreek water will bring down fever and boost immunity."+"\n 5. Immunity boosting foods\n" +
                        "A strong immune system help you prevent dengue and also help in quick recovery from dengue fever. Strong immunity will also treat the initial symptoms of dengue. You must add immunity-boosting foods to your diet like citrus foods, garlic, almonds, turmeric and many more.");
                break;
            case "Typhoid":
                abouttreatment.setText("1. Increase Fluid Intake\n" +
                        "Typhoid fever may cause vomiting and diarrhoea that might lead to severe dehydration. To prevent dehydration, keep sipping on fluids. Staying hydrated also helps in the timely elimination of waste materials and toxins from the body. Apart from water, have fruit juices, coconut water, and soups.\n" +
                        "\n" +
                        "ORS is the best solution to combat the dehydration caused by typhoid fever. Ensure that you have WHO recommended ORS. Buy sachets from any chemist or keep deliciously flavoured tetra packs at home. You can even make it at home by mixing sugar and salt in a litre of boiled water.\n" +
                        "\n" +
                        "2. Use Cold Compresses\n" +
                        "To combat high fever, use cold compresses to bring down the temperature. You can Sponge off armpits, feet, groin, and hands. Applying cold compresses on the extremities brings down the fever most effectively.\n" +
                        "\n" +
                        "You can also soak a washcloth in icy water, wring out the excess water and place it on your forehead. Change the washcloth frequently.\n" +
                        "\n" +
                        "3. Have Apple Cider Vinegar\n" +
                        "Apple cider vinegar helps to maintain a proper pH in the body. It draws out heat from the skin and therefore, reduces the body temperature. The loss of minerals because of diarrhoea is compensated by having apple cider vinegar. Mix a teaspoon of apple cider vinegar in water, add honey if needed. Drink before meals. Read more about the health benefits of apple cider vinegar\n" +
                        "\n" +
                        "4. Basil\n" +
                        "The holy basil is antibiotic and antimicrobial. Add basil to boiled water and drink three to four cups daily. Basil boosts immunity and calms the tummy. Or you can 4-5 basil/tulsi leaves to make a paste. Add pepper powder to this paste and a few strands of saffron or Kesar. Mix all these and divide them into three parts. Have this mixture after every meal. Read more on the health benefits of tulsi (Basil).\n" +
                        "\n" +
                        "5. Garlic\n" +
                        "The antimicrobial properties present in the garlic help fight off typhoid bacteria. Garlic speeds up recovery from typhoid due to its antioxidative properties. It boosts immunity and detoxifies the body. Eat two cloves on an empty stomach. This is not conducive for pregnant women and children. Also, read 10 health benefits of garlic.\n" +
                        "\n" +
                        "6. Bananas\n" +
                        "Bananas can bring down the fever and treat diarrhoea in people with typhoid. Bananas have pectin, a soluble fiber that helps the intestines absorb fluids, thus curing diarrhoea. Potassium in the fruit helps in replacing the electrolytes lost through loose motions. It is one of the best things to eat when having typhoid.\n" +
                        "\n" +
                        "7. Triphala Churan\n" +
                        "This is an essential ayurvedic churan that shows positive effects on fever and typhoid. It hampers the growth of Salmonella typhi. Chemists provide this in the form of powder and tablets. This is one of the best home remedies for typhoid in Ayurveda.\n" +
                        "\n" +
                        "8. Cloves\n" +
                        "Cloves fight against bacteria that cause typhoid. The essential oils in cloves have antibacterial properties that can kill the bacteria that cause typhoid. It also reduces nausea and vomiting caused due to typhoid. Boil water with cloves, strain in a cup and have two cups daily.\n" +
                        "\n" +
                        "9. Pomegranates\n" +
                        "Pomegranates are an effective home remedy against typhoid. It helps in preventing dehydration. Have it as a fruit or take out the juice.\n" +
                        "\n" +
                        "Consult a doctor and use the home remedies as an adjunct therapy. With proper rest, light food, clean water, and patience, the patient will recover quickly.\n" +
                        "\n" +
                        "10. Buttermilk \n" +
                        "\n" +
                        "Buttermilk is loaded with probiotics which not only improves gut health but also supports the immune system. Additionally, it helps to break proteins down which can aid in the recovery process after your fever has passed. This is one of the home remedies for typhoid that you should use towards the end of your fever. \n" +
                        "\n" +
                        "11. Oranges\n" +
                        "\n" +
                        "Oranges are loaded with large amounts of vital vitamins, minerals and nutrients. This helps to replenish your body with the nutrients it needs to get through the fever and get back to normal. Additionally, it contains water which may help to reduce dehydration. Have a few slices or an entire orange if you’re able to, your body will get the vitamins it needs. It’s one of the simpler home remedies for typhoid fever and will leave you feeling refreshed. \n" +
                        "\n" +
                        "12. Guava Leaves\n" +
                        "\n" +
                        "Guava leaves may help to reduce the severity of your fever by restricting the production of mucus along the respiratory tract, throat and lungs. This cuts down on the amount of microbe growth, which can lessen your symptoms. As a bonus, guava leaves are high in iron and vitamin C, both of which your immune system needs.\n" +
                        "\n" +
                        "Tips to follow:\n" +
                        "\n" +
                        "Avoid using items used by the infected person. E.g., towel, glass, napkin, etc.\n" +
                        "Drink only boiled water.\n" +
                        "Do not indulge in sweetened beverages and a big NO to coffee.\n" +
                        "Avoid using raw vegetables and fruits that you cannot peel.\n" +
                        "Consume curd, yogurt, and buttermilk to balance the intestinal bacterial flora.\n" +
                        "Bed rest is essential because the disease causes a lot of weakness.");
                break;
            case "hepatitis A":
                abouttreatment.setText("How to Treat Hepatitis A Symptoms at Home\n" +
                        "Try these tips to care for yourself while you’re waiting for the virus to go away:\n" +
                        "\n" +
                        "Stay in. Until any fever and jaundice have cleared up, your doctor will want you to skip work or school and stay at home.\n" +
                        "Rest up. It’s normal to feel very tired during the first few weeks that you’re sick.\n" +
                        "Take care of your skin. Some people with hepatitis A get very itchy. Keep your house cool, wear loose clothes, and skip very hot baths and showers.\n" +
                        "Eat small meals. This is easier on your stomach than big, heavy meals. It’ll also lessen your chances of feeling queasy or throwing up.\n" +
                        "Get enough calories. A loss of appetite is common. To make sure you’re getting enough nutrients, you may need to choose foods that are high in calories. You could even try drinking fruit juice instead of water.\n" +
                        "Avoid alcohol. Drinking alcohol will strain your liver. You’ll want to avoid it until your doctor gives you the go-ahead.\n" +
                        "Go easy on your liver. While you’re sick, your liver will have a tough time breaking down any drugs, including over-the-counter ones. Ask your doctor what medicines -- including vitamins and supplements -- are safe for you to take.\n" +
                        "Keep your illness to yourself. The hepatitis A virus is easily spread to others. Until you’re well, avoid all sexual activity, even sex with a condom. Don’t prepare food for others. Wash your hands each time you use the toilet or change a diaper.\n" +
                        "Check in with your doctor. They’ll want to make sure you’re coping with your symptoms. They can let you know when you’re well enough to return to your normal routine.");
                break;
            case "Hepatitis B":
                abouttreatment.setText("Many people are interested in using herbal remedies or supplements to boost their immune systems and help their livers. The problem though is that there is no regulation of companies manufacturing these produces, which means there is no rigorous testing for safety or purity. So the quality of the herbal remedy or vitamin supplement may be different from bottle to bottle. Also some herbal remedies could interfere with your prescription drugs for hepatitis B or other conditions; some can even actually damage your liver.\n" +
                        "\n" +
                        "There are many companies that make false promises on the Internet and through social media about their products. Online claims and patient testimonials on Facebook are fake and are used to trick people into buying expensive herbal remedies and supplements. Remember, if it sounds too good to be true, then it’s probably not true.\n" +
                        "\n" +
                        "Below are reliable sources of information about herbs and alternative medicines. This information is based on scientific evidence, not false promises. Check whether the active ingredients in your herbal remedies or supplements are real and if it safe for your liver. The most important thing is to protect your liver from any additional injury or harm.");
                break;
            case "Hepatitis C":
                abouttreatment.setText("Sleep\n" +
                        "Getting enough sleep is important for staying healthy and feeling your best during HCV treatment. Unfortunately, insomnia, or difficulty sleeping, can be one of the side effects of some of the medications.\n" +
                        "\n" +
                        "If you’re having trouble falling or staying asleep, start practicing these good sleeping habits:\n" +
                        "\n" +
                        "Go to bed at the same time and get up at the\n" +
                        "same time each day.\n" +
                        "Avoid caffeine, tobacco, and other stimulants.\n" +
                        "Keep your sleeping room cool.\n" +
                        "Exercise in the early morning or late afternoon,\n" +
                        "but not right before bed.\n" +
                        "Sleeping pills can also be helpful. Talk to your doctor before starting any sleep medications to make sure there are no known interactions with any medications you’re taking.\n" +
                        "\n" +
                        "Nutrition and diet\n" +
                        "Most people with hepatitis C don’t need to follow a special diet, but eating healthy will give you energy and help you feel your best during treatment.\n" +
                        "\n" +
                        "Some medications used to treat hepatitis C may cause you to lose your appetite or feel sick to your stomach.\n" +
                        "\n" +
                        "Ease these symptoms with these tips:\n" +
                        "\n" +
                        "Eat small meals or snacks every three to four\n" +
                        "hours, even if you aren’t hungry. Some people feel less sick when they “graze”\n" +
                        "throughout the day rather than when they eat bigger meals.\n" +
                        "Take a light walk before meals. It might help\n" +
                        "you to feel hungrier and less nauseous.\n" +
                        "Go easy on fatty, salty, or sugary foods.\n" +
                        "Avoid alcohol.\n" +
                        "Mental health\n" +
                        "You may be overwhelmed when you start HCV treatment, and it’s normal to experience feelings of fear, sadness, or anger.\n" +
                        "\n" +
                        "But some medications used to treat hepatitis C can increase your risk of developing these feelings, as well as anxiety and depression.\n" +
                        "\n" +
                        "The effects of DAAs on depression during treatment for hepatitis C infection are unclearTrusted Source. However, depression usually improves after completing a treatment course.\n" +
                        "\n" +
                        "Symptoms of depression can include:\n" +
                        "\n" +
                        "feeling sad, anxious, irritable, or hopeless\n" +
                        "losing interest in the things you usually enjoy\n" +
                        "feeling worthless or guilty\n" +
                        "moving slower than usual or finding it hard to\n" +
                        "sit still\n" +
                        "extreme tiredness or lack of energy\n" +
                        "thinking about death or suicide\n" +
                        "If you have symptoms of depression that don’t go away after two weeks, talk with your doctor. They may recommend taking antidepressant medications or speaking with a trained therapist.\n" +
                        "\n" +
                        "Your doctor may also recommend a hepatitis C support group where you can talk with other people going through treatment. Some support groups meet in person, while others meet online.");
                break;
            case "Hepatitis D":
                abouttreatment.setText("Call your doctor right away if you have symptoms of hepatitis D. If you have symptoms of the disease without jaundice, your doctor may not suspect hepatitis.\n" +
                        "\n" +
                        "To make an accurate diagnosis, your doctor will perform a blood test that can detect anti-hepatitis D antibodies in your blood. If antibodies are found, it means you’ve been exposed to the virus.\n" +
                        "\n" +
                        "Your doctor will also give you a liver function test if they suspect you have liver damage. This is a blood test that evaluates the health of your liver by measuring the levels of proteins, liver enzymes, and bilirubin in your blood. Results from the liver function test will show whether your liver is stressed or damaged."+"There are no known treatments for acute or chronic hepatitis D. Unlike other forms of hepatitis, currentTrusted Source antiviral medications don’t seem to be very effective in treating HDV.\n" +
                        "\n" +
                        "You may be given large doses of a medication called interferon for up to 12 months. Interferon is a type of protein that may stop the virus from spreading and lead to remission from the disease. However, even after treatment, people with hepatitis D can still test positive for the virus. This means that it’s still important to use precautionary measures to prevent transmission. You should also remain proactive by watching for recurring symptoms.\n" +
                        "\n" +
                        "If you have cirrhosis or another type of liver damage, you may need a liver transplant. A liver transplant is a major surgical operation that involves removing the damaged liver and replacing it with a healthy liver from a donor. In cases where a liver transplant is needed, approximately 70 percent of people live 5 years or longer after the operation."+"Hepatitis D isn’t curable. Early diagnosis is essential in preventing liver damage. You should call your doctor right away if you suspect you have hepatitis. When the condition goes untreated, complications are more likely to occur. These include:\n" +
                        "\n" +
                        "cirrhosis\n" +
                        "liver disease\n" +
                        "liver cancer\n" +
                        "People with chronic hepatitis D are more likely to develop complications than those with the acute version of the infection.");
                break;
            case "Hepatitis E":
                abouttreatment.setText("Treatment\n" +
                        "In most cases, hepatitis E goes away on its own in about 4-6 weeks. These steps can help ease your symptoms:\n" +
                        "\n" +
                        "Rest\n" +
                        "Eat healthy foods\n" +
                        "Drink lots of water\n" +
                        "Avoid alcohol\n" +
                        "Check with your doctor before you take any medicine that may damage your liver, such as acetaminophen.\n" +
                        "If you’re pregnant, your doctor may keep you under watch in the hospital. If your condition is serious, you may get medicine to fight the infection."+"Prevention\n" +
                        "No vaccine can prevent the hepatitis E virus. It’s most common in less-developed countries in Asia, the Middle East, Africa, and Central America. You can lower your chances of getting the virus if you:\n" +
                        "\n" +
                        "Don’t drink water or use ice that you don’t know is clean.\n" +
                        "Don’t eat undercooked pork, deer meat, or raw shellfish.\n" +
                        "Wash your hands with soap and water after you use the bathroom, change a diaper, and before you prepare or eat food.");
                break;
            case "Alcoholic hepatitis":
                abouttreatment.setText("Stop drinking alcohol. This is the most critical part of the treatment. It may reverse the disease if your alcoholic hepatitis is mild. Your doctor may recommend medications, therapy, and support groups to help prevent or treat any withdrawal symptoms.\n" +
                        "Change your diet. This may include eating low-sodium foods as well as taking diuretics and vitamin supplements.\n" +
                        "Antibiotics. If you have alcoholic hepatitis, you’re more at risk for bacterial infections. Your doctor will watch for infections and treat it if one appears.\n" +
                        "Steroids. Your doctor may recommend corticosteroid drugs to reduce liver swelling.");
                break;
            case "Tuberculosis":
                abouttreatment.setText("1. Foods rich in vitamins A, C, B complex and E\n" +
                        "\n" +
                        "Foods that are a rich source of vitamins A, C and E like tomato, orange, carrot, mango, sweet pumpkin, guava, amla, and nuts, if added in the diet of a TB patient, can benefit them immensely. Foods with vitamin B complex like fish and chicken can form an important component of their diet.\n" +
                        "\n" +
                        "2. Protein-rich food\n" +
                        "\n" +
                        "TB patients suffer from weight loss and loss in appetite. Thus, protein-rich food like eggs, cottage cheese, and soya can be added in their diets in large quantities to give them energy.\n" +
                        "\n" +
                        "3. Foods that are a storehouse of Zinc\n" +
                        "\n" +
                        "Nuts, sunflower seeds, chia seeds, flax seeds, and pumpkin seeds are a great source of zinc. They provide large amounts of nutrients to the body and help in fighting against diseases like TB.\n" +
                        "\n" +
                        "4. Calorie dense food\n" +
                        "\n" +
                        "Since TB patients undergo weight loss, calorie dense food is the best way to increase their metabolism. Including foods like bananas, peanut chikki, cereal porridge and ragi can prove beneficial.\n" +
                        "\n" +
                        "5. Garlic\n" +
                        "\n" +
                        "Garlic is an effective home remedy for TB. It contains a compound – Allicin –  which is active against the bacterium that causes TB. which helps in fighting tuberculosis. Adding fresh garlic to a regular diet or steeping some garlic in water can work wonders and can help keep our body safe from TB.");
                break;
            case "Common Cold":
                abouttreatment.setText("Chicken soup\n" +
                        "Chicken soup may not be a cure-all, but it’s a great choice when you’re sick. Research suggests that enjoying a bowl of chicken soup with vegetables, prepared from scratch or warmed from a can, can slow the movement of neutrophils in your body. Neutrophils are a common type of white blood cell. They help protect your body from infection. When they’re moving slowly, they stay more concentrated in the areas of your body that require the most healing.\n" +
                        "\n" +
                        "The study found that chicken soup was effective for reducing the symptoms of upper respiratory infections in particular. Low-sodium soup also carries great nutritional value and helps keep you hydrated. It’s a good choice, no matter how you’re feeling.\n" +
                        "\n" +
                        "Ginger\n" +
                        "The health benefits of ginger root have been touted for centuries, but now we have scientific proof of its curative properties. A few slices of raw ginger root in boiling water may help soothe a cough or sore throat. Research suggests that it can also ward off the feelings of nausea that so often accompany influenza. For example, one studyTrusted Source found that just 1 gram of ginger can “alleviate clinical nausea of diverse causes.”\n" +
                        "\n" +
                        "Grab some ginger tea online today and start feeling its soothing benefits.\n" +
                        "\n" +
                        "Honey\n" +
                        "Honey has a variety of antibacterial and antimicrobial properties. Drinking honey in tea with lemon can ease sore throat pain. Research suggests that honey is an effective cough suppressant, too. In one study, researchers found that giving children 10 grams of honey at bedtime reduced the severity of their cough symptoms. The children reportedly slept more soundly, which also helps reduce cold symptoms.\n" +
                        "\n" +
                        "You should never give honey to a child younger than 1 year old, as it often contains botulinum spores. While they’re usually harmless to older children and adults, infants’ immune systems aren’t able to fight them off.\n" +
                        "\n" +
                        "Find a variety of honey on Amazon now.\n" +
                        "\n" +
                        "Garlic\n" +
                        "Garlic contains the compound allicin, which may have antimicrobial properties. Adding a garlic supplement to your diet might reduce the severity of cold symptoms. According to some researchTrusted Source, it might even help you avoid getting sick in the first place.\n" +
                        "\n" +
                        "More research needs to be done on the potential cold-fighting benefits of garlic. In the meantime, adding more garlic to your diet probably won’t hurt.\n" +
                        "\n" +
                        "\n" +
                        "Echinacea\n" +
                        "Native Americans have used the herb and root of the echinacea plant to treat infections for more than 400 years. Its active ingredients include flavonoids, chemicals that have many therapeutic effects on the body. For example, flavonoids can boost your immune system and reduce inflammation.\n" +
                        "\n" +
                        "Research on the herb’s effectiveness at fighting the common cold and flu has been mixed. But one review suggestsTrusted Source that taking echinacea may lower your risk of developing the common cold by more than 50 percent. It may also reduce the length of a cold. If you’re a healthy adult, consider taking 1 to 2 grams of echinacea root or herb as a tea, three times daily, for no longer than one week.\n" +
                        "\n" +
                        "Vitamin C\n" +
                        "Vitamin C plays an important role in your body and has many health benefits. Along with limes, oranges, grapefruits, leafy greens, and other fruits and vegetables, lemons are a good source of vitamin C. Adding fresh lemon juice to hot tea with honey may reduce phlegm when you’re sick. Drinking hot or cold lemonade may also help.\n" +
                        "\n" +
                        "While these drinks may not clear up your cold entirely, they can help you get the vitamin C that your immune system needs. Getting enough vitamin C can relieveTrusted Source upper respiratory tract infections and other illnesses.\n" +
                        "\n" +
                        "Probiotics\n" +
                        "Probiotics are “friendly” bacteria and yeast that are found in your body, some foods, and supplements. They can help keep your gut and immune system healthy, and research indicatesTrusted Source that probiotics may reduce your chance of getting sick with an upper respiratory infection.\n" +
                        "\n" +
                        "For a delicious and nutritious source of helpful bacteria, include probiotic yogurt in your diet. Besides its potential benefits for your immune system, yogurt is a healthy snack that provides plenty of protein and calcium. Look for products that list live bacteria on the label.\n" +
                        "\n" +
                        "Other options\n" +
                        "Salt water\n" +
                        "Gargling with salt water may help preventTrusted Source upper respiratory infections. It may also decrease the severity of cold symptoms. For example, it may ease sore throat pain and nasal congestion.\n" +
                        "\n" +
                        "Gargling with salt water reduces and loosens mucus, which contains bacteria and allergens. To try this remedy at home, dissolve 1 teaspoon of salt in a full glass of water. Swish it around your mouth and throat. Then spit it out.\n" +
                        "\n" +
                        "Vapor rub\n" +
                        "You might not like the smell, but some old-fashioned topical ointments, such as vapor rub, appear to reduce cold symptoms in children older than 2 years. Just one or two applications before bed can help open air passages to combat congestion, reduce coughing, and improve sleep. Vapor rub is gaining traction among some doctors who encourage parents to avoid giving over-the-counter cold medicines to young children because of unwanted side effects.\n" +
                        "\n" +
                        "Humidity\n" +
                        "Influenza thrives and spreads more easily in dry environments. Creating more humidity in your home may reduce your exposure to this flu-causing virus. Increased humidity may also reduce nasal inflammation, making it easier to breathe when you’re sick. Temporarily adding a cool mist humidifier to your bedroom may help you feel more comfortable. This is especially true in winter, when dry indoor heat can exacerbate your symptoms. Adding a few drops of eucalyptus oil might also stimulate your breathing.\n" +
                        "\n" +
                        "Get a humidifier online and start breathing easier.\n" +
                        "\n" +
                        "Remember, the water used in humidifiers needs to be changed daily to stop mold and other fungi from growing. For the same effect without a humidifier, take a long shower or linger in a steamy bathroom.\n" +
                        "\n" +
                        "Warm baths\n" +
                        "Sometimes you can reduce a child’s fever by giving them a warm sponge bath. Warm baths can also reduce cold and flu symptoms in adults. Adding Epsom salt and baking soda to the water can reduce body aches. Adding a few drops of essential oil, such as tea tree, juniper, rosemary, thyme, orange, lavender, or eucalyptus, may also have a soothing effect.");
                break;
            case "Pneumonia":
                abouttreatment.setText("1. Try a saltwater gargle\n" +
                        "Gargling with salt water can help remove some of the mucus in your throat and relieve irritation.\n" +
                        "\n" +
                        "How to do a saltwater gargle\n" +
                        "To do this:\n" +
                        "\n" +
                        "Dissolve 1/4 to 1/2 teaspoon salt into a glass of warm water.\n" +
                        "Gargle the mixture with your head raised, looking at the ceiling.\n" +
                        "Spit it out.\n" +
                        "Repeat at least three times each day.\n" +
                        "2. Drink hot peppermint tea\n" +
                        "Peppermint can also help alleviate irritation and expel mucus. Research suggests that it can be an effective decongestant, anti-inflammatory, and painkiller.\n" +
                        "\n" +
                        "If you don’t already have peppermint tea, you can pick up loose or bagged teas at your local grocery or online. And if you have fresh peppermint, you can easily make your own tea.\n" +
                        "\n" +
                        "How to make fresh peppermint tea\n" +
                        "To make peppermint tea from scratch:\n" +
                        "\n" +
                        "Wash and cut fresh mint leaves and place them in a cup or teapot.\n" +
                        "Add boiling water and steep for about 5 minutes.\n" +
                        "Strain and serve with lemon, honey, or milk.\n" +
                        "You may wish to deeply inhale the aroma of the peppermint tea while the tea is steeping. This might help clear your nasal pathways.\n" +
                        "\n" +
                        "\n" +
                        "For shortness of breath\n" +
                        "With pneumonia, your breathing may suddenly become rapid and shallow, or this symptom could develop gradually over the course of a few days.\n" +
                        "\n" +
                        "You may even experience breathlessness while you’re resting. Your doctor may prescribe medication or inhalers to help. Even as you try the suggestions below, make sure you keep up with your physician’s instructions and dosages.\n" +
                        "\n" +
                        "If the following suggestions don’t help and your breath becomes even shorter, seek immediate medical care.\n" +
                        "\n" +
                        "3. Use a handheld fan\n" +
                        "While the evidence is thin, a 2021 reviewTrusted Source suggests that passing a handheld fan across the face may temporarily relieve breathlessness in people with chronic obstructive pulmonary disorder (COPD).\n" +
                        "\n" +
                        "While the underlying cause of breathing difficulties is different in people with pneumonia, you might find that using a fan makes it easier to catch your breath.\n" +
                        "\n" +
                        "You can use a handheld fan until your symptoms subside.\n" +
                        "\n" +
                        "4. Drink a cup of coffee\n" +
                        "Drinking a cup of coffee may also help relieve shortness of breath. Caffeine may help widen the airways, and a 2021 reviewTrusted Source even suggested that consuming it could help soothe some COVID-19 symptoms and work against SARS-CoV-2.\n" +
                        "\n" +
                        "Caffeine’s half-life is 3-5 hours, meaning that your body gets rid of half the caffeine content in this time. If caffeine helps to widen your airways, this is the amount of time it’s likely to have its most noticeable effects.\n" +
                        "\n" +
                        "For chest pain\n" +
                        "Chest pain may come on suddenly or over the course of several days. You should expect some chest pain or ache if you get pneumonia. With treatment, any chest pain typically subsides within 4 weeks.\n" +
                        "\n" +
                        "5. Drink a cup of turmeric tea\n" +
                        "A 2020 reviewTrusted Source suggests that a compound called curcumin in turmeric has anti-inflammatory, antioxidant, and antimicrobial qualities that can help your body defend itself against pneumonia.\n" +
                        "\n" +
                        "Another review from 2018 supported curcumin’s activity against pain, meaning that it might provide some relief for pneumonia’s sometimes intense chest pain (even though the research didn’t focus on chest pain directly).\n" +
                        "\n" +
                        "You can buy turmeric tea at your local grocery or online. You can also make your own tea using turmeric powder.\n" +
                        "\n" +
                        "Making turmeric tea for pneumonia chest pain\n" +
                        "To make fresh tea:\n" +
                        "\n" +
                        "Add 1 teaspoon of turmeric powder to a few cups of boiling water.\n" +
                        "Reduce the heat and slowly simmer for 10 minutes.\n" +
                        "Strain and serve with honey and lemon.\n" +
                        "Add a pinch of black pepper for improved absorption.\n" +
                        "Drink as often as you’d like.\n" +
                        "6. Drink a cup of ginger tea\n" +
                        "Ginger has also demonstrated anti-inflammatory and pain-relieving properties in recent researchTrusted Source. As with turmeric, current research on ginger hasn’t looked at whether it helps specifically with chest pain, but it’s a harmless, hydrating way to try and soothe the uncomfortable effects of pneumonia.\n" +
                        "\n" +
                        "You can find loose or bagged ginger teas at your local grocery or online. Or, you can use raw ginger to make your own ginger tea.\n" +
                        "\n" +
                        "How to make ginger tea for pneumonia chest pain\n" +
                        "To make fresh tea:\n" +
                        "\n" +
                        "Cut or grate a few pieces of fresh ginger and add it to a pot of boiling water.\n" +
                        "Reduce the heat and simmer for about 20 minutes.\n" +
                        "Strain and serve with honey and lemon.\n" +
                        "Drink as often as you’d like.\n" +
                        "For fever\n" +
                        "Your fever may develop suddenly or over the course of a few days. With treatment, it should subside within the week.\n" +
                        "\n" +
                        "7. Take an over-the-counter pain reliever\n" +
                        "Over-the-counter (OTC) pain relievers like ibuprofen (Advil) can help to reduce your fever and alleviate pain.\n" +
                        "\n" +
                        "If you can, take any pain relievers with food or on a full stomach. This helps reduce your risk of side effects, such as nausea.\n" +
                        "\n" +
                        "Adults can typically take one or two 200-milligramTrusted Source (mg) capsules every 4 to 6 hours. You shouldn’t exceed 1,200 mg per day.\n" +
                        "\n" +
                        "For children, follow the directions on the packaging.\n" +
                        "\n" +
                        "8. Drink some fenugreek tea\n" +
                        "Research from 2018 found that fenugreek tea can stimulate sweating when you drink it. As sweat cools you down, this might help provide some relief from a fever.\n" +
                        "\n" +
                        "9. Stay hydrated\n" +
                        "Drinking enough water and electrolytes while you have a fever can help you prevent dehydration. Eating homemade popsicles or sipping on chilled beverages also provides hydration as well as cooling you down.\n" +
                        "\n" +
                        "10. Apply a lukewarm compress or take a lukewarm bath\n" +
                        "Submerging your body in a lukewarm bath might help you bring down your body temperature.\n" +
                        "\n" +
                        "You can also use a lukewarm compress to help cool your body from the outside inward if a bath is not convenient. Although it may be tempting to use a cold compress, the sudden temperature shift can cause chills. A lukewarm compress provides a more gradual, comfortable temperature change.\n" +
                        "\n" +
                        "Making a lukewarm compress\n" +
                        "To make a compress:\n" +
                        "\n" +
                        "Wet a small towel or washcloth with lukewarm water.\n" +
                        "Wring out the excess water and place the compress on your forehead.\n" +
                        "Repeat as often as you’d like.\n" +
                        "For chills\n" +
                        "Chills may come on before or during a fever. They typically subside after your fever breaks. This may last up to a week, depending on when you begin treatment for pneumonia.\n" +
                        "\n" +
                        "11. Drink warm water\n" +
                        "If peppermint tea isn’t your thing, a glass of warm water will do. This can help you stay hydrated and warm you internally.\n" +
                        "\n" +
                        "12. Have a bowl of soup\n" +
                        "Not only is a hot bowl of soup nourishing, but it can also help replenish vital liquids while it warms you from the inside out.\n" +
                        "\n" +
                        "\n" +
                        "Stick to your treatment plan\n" +
                        "The typical pneumonia treatment plan consists of rest, antibiotics, and increased fluid intake. You should take it easy even if your symptoms begin to subside.\n" +
                        "\n" +
                        "Depending on the cause of pneumonia, your doctor may prescribe an antiviral medication instead of an antibiotic.\n" +
                        "\n" +
                        "You should take the entire course of medication even after you begin seeing improvement. If you don’t see improvement within 3 days, see your doctor.\n" +
                        "\n" +
                        "Lifestyle tips for feeling better during pneumonia\n" +
                        "Drink at least 8 cups of water or liquid per day. Liquids help to thin mucous and keep your fever down.\n" +
                        "Get enough rest. Your body needs extra time to recuperate and heal properly. Adequate rest can also help prevent relapse.\n" +
                        "Follow a healthy diet plan that includes all food groups. During recovery, it’s recommended that you eat six smaller meals daily instead of three larger ones.\n" +
                        "Preventing pneumonia naturally\n" +
                        "Pneumonia isn’t always preventable. But by adopting certain lifestyle adjustments or avoiding triggers, you may be able to reduce your risk of experiencing its more severe effects.\n" +
                        "\n" +
                        "Such measures includeTrusted Source:\n" +
                        "\n" +
                        "washing your hands thoroughly and regularly to reduce your risk for infection\n" +
                        "avoiding cigarette smoke or quitting if you already smoke tobacco\n" +
                        "where possible, avoiding areas with high levels of air pollution\n" +
                        "eating a nutritious and balanced diet\n" +
                        "staying active and exercising regularly\n" +
                        "stress relief\n" +
                        "maintaining a regular sleep schedule and good sleep hygiene\n" +
                        "sticking to any prescribed treatments or interventions from a healthcare professional");
                break;
            case "Dimorphic hemmorhoids(piles)":
                abouttreatment.setText("Warm bath with Epsom salt\n" +
                        "Warm baths can help soothe irritation from hemorrhoids. Try using a sitz bath — a small plastic tub that fits over a toilet seat so you can just immerse the affected area — or take a bath in a tub, if you can.\n" +
                        "\n" +
                        "Try a warm bath or a sitz bath for 20 minutes after each bowel movement. Adding Epsom salt to the bath can provide further relief by reducing pain.\n" +
                        "\n" +
                        "Cold compresses\n" +
                        "Apply ice packs or cold compresses to the anus to relieve swelling for 15 minutes at a time. For large, painful hemorrhoids, cold compresses can be an extremely effective treatment.\n" +
                        "\n" +
                        "Be sure to wrap ice inside a cloth or paper towel. Never apply something frozen directly to your skin, since this can harm or damage the skin.\n" +
                        "\n" +
                        "Witch hazel\n" +
                        "Witch hazel can reduce both itching and pain, the two main symptoms of external hemorrhoids. It’s a natural anti-inflammatory, so it can also reduce swelling.\n" +
                        "\n" +
                        "You can buy it in a liquid form that you can apply directly to external hemorrhoids. You can also find it in products like anti-itch wipes and soaps.\n" +
                        "\n" +
                        "Shop for witch hazel products online.\n" +
                        "\n" +
                        "Aloe vera\n" +
                        "Aloe vera gel is often used to treat hemorrhoids and skin conditions. It’s thought to have anti-inflammatory properties that might help reduce irritation.\n" +
                        "\n" +
                        "There isn’t much clinical evidence on the effectiveness of aloe vera gel for hemorrhoids. But the National Center for Complimentary and Integrated HealthTrusted Source lists it as likely very safe for topical use.\n" +
                        "\n" +
                        "Aloe vera gel can be found as an ingredient in other products like sunscreen or lotion. But you should only use pure aloe vera gel on hemorrhoids, since other ingredients and additives can irritate hemorrhoids. Pure aloe vera gel can also be harvested directly from inside an aloe plant’s leaves.\n" +
                        "\n" +
                        "Some people are allergic to aloe vera, especially those who are allergic to garlic or onions. Check for allergic reaction using the following steps:\n" +
                        "\n" +
                        "Rub a dime-sized amount onto your forearm.\n" +
                        "Wait 24 to 48 hours.\n" +
                        "If no reaction occurs, it should be safe to use.\n" +
                        "Soothing wipes\n" +
                        "Using toilet paper after a bowel movement can irritate existing hemorrhoids. Moistened wipes help keep you clean without causing further irritation.\n" +
                        "\n" +
                        "You can also use wipes that have soothing anti-hemorrhoid ingredients, such as witch hazel or aloe vera.\n" +
                        "\n" +
                        "Make sure that the wipes you choose don’t have alcohol, perfume, or other irritants in them. These substances can make hemorrhoid symptoms worse rather than relieve them.\n" +
                        "\n" +
                        "Loose cotton clothing\n" +
                        "Swap out tight, polyester clothes with breathable cotton (especially cotton underwear) to help keep the anal area both clean and dry. This can potentially reduce symptoms and the risk of infections in open sores or raw, damaged skin.\n" +
                        "\n" +
                        "Avoid using perfumed detergents or fabric softeners to help reduce irritation.\n" +
                        "\n" +
                        "Tea tree oil\n" +
                        "Tea tree oil is a natural antiseptic that may help relieve pain, itching, and discomfort. Tea tree oil may also help fight against bacteria that could otherwise lead to infections in damaged or irritated skin.\n" +
                        "\n" +
                        "There is not lot of research available on the effectiveness of tea tree oil for hemorrhoids, but one 2012 study found that a 2-week hemorrhoid treatment combining tea tree oil, hyaluronic acid, and methyl-sulfonyl-methane (often used to treat arthritis) helped significantly reduce pain, bleeding, and inflammation compared with a placebo.\n" +
                        "\n" +
                        "Use tea tree oil along with other natural treatments, like witch hazel or aloe, for additional relief.\n" +
                        "\n" +
                        "Coconut oil\n" +
                        "Coconut oil has strong anti-inflammatory properties, according to 2008 research, which can reduce inflammation and swelling. It’s analgesic (pain-relieving) properties can help to reduce discomfort caused by the hemorrhoids. Its antibacterial properties allow hemorrhoids to heal faster, according to 2014 research.\n" +
                        "\n" +
                        "Coconut oil may also aid in relieving constipation, thanks to a laxative effect. Since constipation or straining during bowel movements is a common cause of hemorrhoids, this can help to treat and prevent them.\n" +
                        "\n" +
                        "You can take coconut oil for hemorrhoids by consuming it regularly or by applying it externally. You can cook with the oil, apply it to external hemorrhoids with a cotton ball, add it to your bath, and more.");
                break;
            case "Heart attack":
                abouttreatment.setText("Almonds\n" +
                        "When heart pain occurs after eating, acid reflux or gastroesophageal reflux disease (GERD) may be to blame. Both conditions may cause intense chest pain. Many people claim that eating a handful of almonds or drinking almond milk when heartburn strikes eases symptoms.\n" +
                        "\n" +
                        "The evidence is anecdotal and there’s not enough scientific data to support this claim. Almonds are an alkaline food and in theory, they may help to soothe and neutralize acid in the esophagus.\n" +
                        "\n" +
                        "On the other hand, almonds are high in fat. For some people, fat triggers acid reflux. Fatty foods may cause the lower esophageal sphincter to relax and allow acid to flow backwards into the esophagus.\n" +
                        "\n" +
                        "Apple cider vinegar\n" +
                        "Drinking a tablespoon of apple cider vinegar with a glass of water before meals or when heart pain strikes is another home remedy for acid reflux. There’s little scientific evidence to show that apple cider vinegar eases heartburn. Still, many people swear it works.\n" +
                        "\n" +
                        "Some people experience acid reflux because their stomach doesn’t produce enough acid. In this case, apple cider vinegar may help by increasing the amount of acid in the stomach. The compound that gives apple cider vinegar its tang is acetic acid. It may help break down food and support digestion.\n" +
                        "\n" +
                        "Apple cider vinegar doesn’t cause side effects in most people. However, it may thin your blood and should be used with caution if you take blood thinners.\n" +
                        "\n" +
                        "Shop for apple cider vinegar.\n" +
                        "\n" +
                        "Drinking a hot drink\n" +
                        "Gas is a common cause of chest pain. A hot or warm drink may help rev up your digestive system and ease gas and bloating. Hot hibiscus tea, in particular, supports digestion and heart health. Research shows hibiscus helps lower blood pressure and reduces cholesterol and triglycerides. Hibiscus is generally recognized as safe to consume.\n" +
                        "\n" +
                        "Shop for hibiscus tea.\n" +
                        "\n" +
                        "Apply a cold pack\n" +
                        "Heart pain is sometimes caused by chest muscle strain. Weightlifting, a fall, or even carrying a child or a heavy laundry basket may all be culprits. Costochondritis, which is an inflammation of the chest wall, is often the source of severe chest pain. Applying a cold pack several times a day to the affected area may help reduce inflammation and ease pain.\n" +
                        "\n" +
                        "Learn more: What are the differences between heartburn, acid reflux, and GERD? »\n" +
                        "\n" +
                        "When to call emergency service\n" +
                        "Home remedies shouldn’t be used as a first-line treatment for chest pain. Any chest pain that’s unusual for you should be medically evaluated.\n" +
                        "\n" +
                        "If you experience persistent chest pain with or without other symptoms — such as nausea, shortness of breath, and sweating — call emergency services immediately. You may be having a heart attack.\n" +
                        "\n" +
                        "Heart attack symptoms may escalate quickly. You should wait for an ambulance to arrive or, in some situations, meet you en route. Emergency services personnel are trained and equipped to handle worsening medical situations that may happen en route to the hospital.\n" +
                        "\n" +
                        "Home remedies to promote overall heart health\n" +
                        "Some home remedies don’t bring rapid relief of heart pain, but work to improve your heart health over the long term. Lifestyle factors such as a healthy diet rich in fruits and vegetables, regular exercise, and not smoking are well-known remedies for improving heart health.\n" +
                        "\n" +
                        "Several supplements can also help keep your heart healthy and strong. The quality of supplements varies, so only buy them from reputable manufacturers. Follow dosage instructions on the bottle to limit your risk of side effects. Supplements include the following:\n" +
                        "\n" +
                        "Omega-3 fatty acids\n" +
                        "Omega-3 fatty acids may help:\n" +
                        "\n" +
                        "reduce your risk of deadly heart arrhythmias\n" +
                        "reduce your triglyceride levels\n" +
                        "reduce the progression of atherosclerosis\n" +
                        "lower your blood pressure\n" +
                        "Omega-3s are found in fatty fish such as salmon, mackerel, and albacore tuna. If you’re unable to eat two servings of fish per week, you can take fish oil supplements high in omega-3s.\n" +
                        "\n" +
                        "Shop for fish oil supplements.\n" +
                        "\n" +
                        "Pomegranate juice\n" +
                        "Adding pomegranate juice to your diet may be beneficial to your heart. Pomegranates are high in antioxidants, which can help keep cholesterol in check and keep your arteries healthy.\n" +
                        "\n" +
                        "According to the Cleveland Clinic, research shows pomegranate juice may help decrease “bad” cholesterol (LDL) in your blood. It may also help prevent or reduce plaque build-up in your arteries, which can cause reduced blood flow to your heart.\n" +
                        "\n" +
                        "At least one studyTrusted Source has found that drinking pomegranate juice helps lower blood pressure.\n" +
                        "\n" +
                        "Shop for pomegranate juice.\n" +
                        "\n" +
                        "Capsaicin\n" +
                        "Capsaicin is the chemical responsible for giving peppers their spicy kick.\n" +
                        "\n" +
                        "According to a 2015 studyTrusted Source, capsaicin may have a wide range of benefits that help protect the heart by:\n" +
                        "\n" +
                        "increasing exercise time in people with angina (when applied topically)\n" +
                        "slowing the development of atherosclerosis\n" +
                        "reducing the risk of metabolic syndrome\n" +
                        "lowering blood pressure\n" +
                        "controlling blood sugar\n" +
                        "reducing the risk of heart muscle thickening\n" +
                        "supporting weight loss\n" +
                        "Many studies on capsaicin were done on rodents. More human trials are needed.\n" +
                        "\n" +
                        "In the meantime, current research suggests taking around 20 milligrams (mg) of capsaicin capsules daily and supplementing your diet with spicy foods and hot sauce. Keep in mind that for some people, eating spicy foods may cause digestive problems.\n" +
                        "\n" +
                        "Garlic\n" +
                        "Both fresh garlic and garlic supplements have been used for years to battle heart problems. ResearchTrusted Source has shown garlic extract may help prevent plaque build-up in the arteries and even reverse heart disease.\n" +
                        "\n" +
                        "The downside? Like fresh garlic, some garlic supplements leave your breath smelling less than fresh. If you can’t get past the smell, look for odor-free garlic capsules.\n" +
                        "\n" +
                        "CoQ10\n" +
                        "Coenzyme Q10 (CoQ10) is a substance your body makes naturally and is critical to heart health. As you age, your body makes less CoQ10. Low levels of CoQ10 in the body have been linked to chronic heart failure. CoQ10 may also help lower blood pressure and prevent exercise-induced chest pain.\n" +
                        "\n" +
                        "Ginger\n" +
                        "Spicy ginger is thought to have anti-inflammatory and antioxidant abilities.\n" +
                        "\n" +
                        "It may help:\n" +
                        "\n" +
                        "lower blood pressure\n" +
                        "reduce cholesterol\n" +
                        "reduce triglycerides\n" +
                        "prevent blood clotting\n" +
                        "Ginger is known for soothing your tummy and reducing gas. It’s also a natural blood thinner, so avoid using it if you take prescription blood thinners.\n" +
                        "\n" +
                        "Curcumin\n" +
                        "According to a 2013 reviewTrusted Source of clinical trials, curcumin, the compound that gives turmeric its golden color, may help reduce inflammation that leads to heart disease. It may also reduce total cholesterol and bad cholesterol in the body while increasing good cholesterol. This can help prevent atherosclerosis.");
                break;
            case "Varicose veins":
                abouttreatment.setText("1. Exercise\n" +
                        "\n" +
                        "Regular exercise encourages better blood circulation in the legs, which helps to push along the blood that has collected in the veins. Exercise also helps to lower a person’s blood pressure, which is another contributing factor to varicose veins.\n" +
                        "\n" +
                        "Low-impact exercises help to get the calf muscles working without excessive strain. Effective, low-impact exercises include:\n" +
                        "\n" +
                        "swimming\n" +
                        "walking\n" +
                        "cycling\n" +
                        "yoga\n" +
                        "2. Compression stockings\n" +
                        "\n" +
                        "Compression stockings are available from most pharmacies and can help by applying pressure to the legs. This aids the muscles and veins to move blood toward the heart.\n" +
                        "\n" +
                        "A study from 2018 found that people who used knee-high compression stockings with a pressure of 18 to 21 mmHg for one week, reported a reduction in the pain and aching associated with varicose veins.\n" +
                        "\n" +
                        "Compression stockings can be found in pharmacies or online stores.\n" +
                        "\n" +
                        "3. Plant extracts\n" +
                        "\n" +
                        "A review study from 2006 suggests that horse chestnut extract, Aesculus hippocastanum L., may help to reduce leg pain, heaviness, and itching in people with chronic venous insufficiency, which is a major cause of varicose veins. Aesculus hippocastanum L. is available to purchase in health stores and online.\n" +
                        "\n" +
                        "A review study from 2010Trusted Source reports that sea pine extract, Pinus maritima, and Butcher’s broom extract, Ruscus aculeatus, may both reduce leg swelling, or edema, that is often associated with varicose veins. Ruscus aculeatus is available to purchase in health stores and online.\n" +
                        "\n" +
                        "Plant extracts and essential oils should be diluted in carrier oils before being applied topically or used in a diffuser for aromatherapy.\n" +
                        "\n" +
                        "4. Dietary changes\n" +
                        "\n" +
                        "Potassium-high foods, such as almonds and pistachio nuts, can help varicose veins by reducing water retention in the body.\n" +
                        "Salty or sodium-rich foods can cause the body to retain water, so cutting down on salty food can minimize water retention. Foods high in potassium can help to reduce water retention.\n" +
                        "\n" +
                        "Foods that are high in potassium include:\n" +
                        "\n" +
                        "almonds and pistachio nuts\n" +
                        "lentils and white beans\n" +
                        "potatoes\n" +
                        "leafy vegetables\n" +
                        "some fish, such as salmon and tuna\n" +
                        "Foods with fiber help to keep the bowels moving and prevent constipation. This may be important, as straining can aggravate damaged valves or make them worse.\n" +
                        "\n" +
                        "Foods that are high in fiber include:\n" +
                        "\n" +
                        "nuts, seeds, and legumes\n" +
                        "oats, wheat, and flaxseed\n" +
                        "whole-grain foods\n" +
                        "People who are overweight are more likely to experience varicose veins, therefore, shedding any excess pounds can reduce the pressure on the veins and alleviate swelling and discomfort.\n" +
                        "\n" +
                        "5. Eat more flavonoids\n" +
                        "\n" +
                        "Adding foods that contain flavonoids may also help a person to shrink their varicose veins.\n" +
                        "\n" +
                        "Flavonoids improve blood circulation, which will keep the blood flowing, and make it less likely to pool in the veins. They also help to reduce blood pressure in the arteries and can relax blood vessels, all of which can reduce varicose veins.\n" +
                        "\n" +
                        "Foods that contain flavonoids include:\n" +
                        "\n" +
                        "vegetables, including onions, bell peppers, spinach, and broccoli\n" +
                        "citrus fruits and grapes, cherries, apples, and blueberries\n" +
                        "cocoa\n" +
                        "garlic");
                break;
            case "Hypothyroidism":
                abouttreatment.setText("Natural remedies\n" +
                        "The goal of natural remedies or alternative medicine is to fix the root cause of the thyroid problem. Thyroid problems sometimes start as the result of:\n" +
                        "\n" +
                        "poor diet\n" +
                        "stress\n" +
                        "missing nutrients in your body\n" +
                        "Changing your diet and taking an herbal supplement are two ways you can help your thyroid condition. These options may have fewer side effects than taking thyroid medication.\n" +
                        "\n" +
                        "Also, taking an herbal supplement to help address a low or underactive thyroid may be helpful for people who aren’t responding well to medications.\n" +
                        "\n" +
                        "Consider the following five natural remedies as additions or alternatives to your treatment plan.\n" +
                        "\n" +
                        "\n" +
                        "Selenium\n" +
                        "According to the National Institutes of Health (NIH)Trusted Source, selenium is a trace element that plays a part in thyroid hormone metabolism.\n" +
                        "\n" +
                        "Many foods contain selenium, including:\n" +
                        "\n" +
                        "tuna\n" +
                        "turkey\n" +
                        "Brazil nuts\n" +
                        "grass-fed beef\n" +
                        "Hashimoto’s thyroiditis, an immune system attack on the thyroid, often reduces the body’s selenium supply. Supplementing this trace element has shown to help balance thyroxine, or T4, levels in some people.\n" +
                        "\n" +
                        "It’s important to talk with your doctor about how much selenium may be right for you since every person is different.\n" +
                        "\n" +
                        "Sugar-free diet\n" +
                        "Sugar and processed foods can lead to increased inflammation in the body.\n" +
                        "\n" +
                        "Inflammation can slow down the conversion of T4 to triiodothyronine, or T3, another thyroid hormone. This can make your symptoms and thyroid disease worsen.\n" +
                        "\n" +
                        "Also, sugar only boosts your energy level in the short term, eliminating it from your diet may help regulate your energy levels. Additionally, removing sugar from your diet may help your stress levels and skin.\n" +
                        "\n" +
                        "It’s not easy to adopt a sugar-free diet, but the benefit to your thyroid health may be worth it.\n" +
                        "\n" +
                        "Vitamin B\n" +
                        "Taking certain vitamin supplements can have an effect on your thyroid health.\n" +
                        "\n" +
                        "Low thyroid hormones can affect your body’s vitamin B-12 levels. Taking a vitamin B-12 supplement may help you repair some of the damage hypothyroidism caused.\n" +
                        "\n" +
                        "Vitamin B-12 can help with the tiredness thyroid disease can cause. The disease also affects your vitamin B-1 levels. You can add more B vitamins to your diet with the following foods:\n" +
                        "\n" +
                        "peas and beans\n" +
                        "asparagus\n" +
                        "sesame seeds\n" +
                        "tuna\n" +
                        "cheese\n" +
                        "milk\n" +
                        "eggs\n" +
                        "Vitamin B-12 is generally safe for most healthy individuals at recommended levels. Talk with your doctor about how much vitamin B-12 may be right for you.\n" +
                        "\n" +
                        "Probiotics\n" +
                        "The NIH studied the link between hypothyroidism and small intestine problems.\n" +
                        "\n" +
                        "It was found that altered gastrointestinal (GI) motility commonly seen with hypothyroidism can cause small intestinal bacterial overgrowth (SIBO) and ultimately lead to chronic GI symptoms, such as diarrhea.\n" +
                        "\n" +
                        "Probiotic supplements contain live helpful bacteria that can help keep your stomach and intestines healthy.\n" +
                        "\n" +
                        "Besides supplement forms, fermented food and drink, such as kefir, kombucha, some cheeses, and yogurt contain useful probiotics.\n" +
                        "\n" +
                        "However, the Food and Drug Administration hasn’t approved the use of probiotics for the prevention or treatment of any condition. Talk with your doctor to see if these supplements might help you.\n" +
                        "\n" +
                        "\n" +
                        "Gluten-free diet\n" +
                        "Adopting a gluten-free diet is more than a fad for many people with hypothyroidism.\n" +
                        "\n" +
                        "According to the National Foundation for Celiac Awareness, a significant number of people with thyroid disease also have celiac disease.\n" +
                        "\n" +
                        "Celiac disease is a digestive disorder in which gluten triggers an immune response in the small intestines.\n" +
                        "\n" +
                        "Research doesn’t currently support a gluten-free diet for the treatment of thyroid disease.\n" +
                        "\n" +
                        "However, many people with Hashimoto’s thyroiditis and hypothyroidism do feel better after removing wheat and other gluten-containing foods from their diet.\n" +
                        "\n" +
                        "But there are some drawbacks to going gluten free. For one, the cost of buying gluten-free foods is often much higher than foods containing wheat.\n" +
                        "\n" +
                        "Also, some prepackaged, gluten-free foods aren’t healthy. That’s because these foods can have a higher fat content and less fiber than wheat- containing products.");
                break;
            case "Hyperthyroidism":
                abouttreatment.setText("L-carnitine\n" +
                        "A natural supplement that may help treat the effects of hyperthyroidism is L-carnitine.\n" +
                        "\n" +
                        "L-carnitine is an amino acid derivative that naturally occurs in the body. It’s often found in weight loss supplements.\n" +
                        "\n" +
                        "It’s also found in foods like meat, fish, and dairy products. Learn about the benefits of L-carnitine here.\n" +
                        "\n" +
                        "Carnitine prevents thyroid hormones from entering certain cells. A 2001 study suggests that L-carnitine can reverse and prevent the symptoms of hyperthyroidism, including heart palpitations, tremors, and fatigue.\n" +
                        "\n" +
                        "While this research is promising, there aren’t enough studies to verify whether L-carnitine is an effective hyperthyroidism treatment.\n" +
                        "\n" +
                        "Bugleweed\n" +
                        "Bugleweed is a plant that’s historically been used to treat heart and lung conditions.\n" +
                        "\n" +
                        "Some sources suggest that bugleweed is a thyrosuppressant — that is, it reduces the function of the thyroid gland.\n" +
                        "\n" +
                        "Unfortunately, there isn’t enough information out there to verify whether it’s an effective treatment for hyperthyroidism or not.\n" +
                        "\n" +
                        "If you choose to use an herbal supplement like bugleweed, follow the manufacturer’s guidelines for dose and frequency and speak with your doctor before starting anything new.\n" +
                        "\n" +
                        "B-complex or B-12\n" +
                        "If you have hyperthyroidism, there’s a chance you have a vitamin B-12 deficiency, too. A vitamin B-12 deficiency can lead you to feel fatigued, weak, and dizzy.\n" +
                        "\n" +
                        "If you have a vitamin B-12 deficiency, your doctor might suggest that you take a B-12 supplement or have a B-12 injection.\n" +
                        "\n" +
                        "While vitamin B-12 supplements can help you manage some of these symptoms, they don’t treat hyperthyroidism on their own.\n" +
                        "\n" +
                        "Although B-12 and B-complex vitamins are available over the counter, it’s best to talk to your doctor before adding in a new supplement.\n" +
                        "\n" +
                        "\n" +
                        "Selenium\n" +
                        "Some researchTrusted Source suggests that selenium can be used to treat the symptoms of hyperthyroidism.\n" +
                        "\n" +
                        "Selenium is a mineral that naturally occurs in water, soil, and foods like nuts, fish, beef, and grains. It can also be taken as a supplement.\n" +
                        "\n" +
                        "Graves’ disease, the most common cause of hyperthyroidism, is associated with thyroid eye disease (TED), which can be treated with selenium. Remember, though, that not everyone with hyperthyroidism has TED.\n" +
                        "\n" +
                        "Other studies have suggested selenium alone isn’t an effective treatment for hyperthyroidism. Overall, the research remains mixedTrusted Source.\n" +
                        "\n" +
                        "It’s best to consult your doctor before taking a supplement like selenium, as there are some possible side effects and selenium shouldn’t be taken in combination with certain medications.\n" +
                        "\n" +
                        "Lemon balm\n" +
                        "Lemon balm, a plant that’s a member of the mint family, is thought to be a treatment for Graves’ disease. In theory, this is because it reduces thyroid-stimulating hormone (TSH).\n" +
                        "\n" +
                        "However, there’s a lack of research on this claim. There’s insufficient evidence to assess whether lemon balm effectively treats hyperthyroidism.\n" +
                        "\n" +
                        "Lemon balm can be consumed as a tea or in the form of a supplement. Setting down with a cup of lemon balm tea may at least be healing as a stress management technique.\n" +
                        "\n" +
                        "Lavender and sandalwood essential oils\n" +
                        "While many people swear by using essential oils to manage the symptoms of hyperthyroidism, there’s insufficient research on this claim.\n" +
                        "\n" +
                        "Lavender and sandalwood essential oils can, for example, reduce feelings of anxiety and help you feel calm. This might help you fight nervousness and sleeplessness, both symptoms of hyperthyroidism.\n" +
                        "\n" +
                        "Beyond that, there isn’t enough research out there to suggest that essential oils could help treat hyperthyroidism.");
                break;
            case "Hypoglycemia":
                abouttreatment.setText("Treatment\n" +
                        "Immediate treatment\n" +
                        "If you have symptoms of hypoglycemia, do the following:\n" +
                        "\n" +
                        "Eat or drink 15 to 20 grams of fast-acting carbohydrates. These are sugary foods without protein or fat that are easily converted to sugar in the body. Try glucose tablets or gel, fruit juice, regular — not diet — soft drinks, honey, and sugary candy.\n" +
                        "Recheck blood sugar levels 15 minutes after treatment. If blood sugar levels are still under 70 mg/dL (3.9 mmol/L), eat or drink another 15 to 20 grams of fast-acting carbohydrate, and recheck the blood sugar level again in 15 minutes. Repeat these steps until the blood sugar is above 70 mg/dL (3.9 mmol/L).\n" +
                        "Have a snack or meal. Once your blood sugar is normal, eating a snack or meal can help stabilize it and replenish your body's glycogen stores.\n" +
                        "Immediate treatment of severe hypoglycemia\n" +
                        "Hypoglycemia is considered severe if you need help from someone to recover. For example, if you can't eat, you might need glucagon injection or intravenous glucose.\n" +
                        "\n" +
                        "In general, people with diabetes who are treated with insulin should have a glucagon kit for emergencies. Family and friends need to know where to find the kit and how to use it in case of emergency.\n" +
                        "\n" +
                        "If you're helping someone who is unconscious, don't try to give the person food or drink. If there's no glucagon kit available or you don't know how to use it, call for emergency medical help.\n" +
                        "\n" +
                        "Treatment of an underlying condition\n" +
                        "Preventing recurrent hypoglycemia requires your doctor to identify the underlying condition and treat it. Depending on the underlying cause, treatment may involve:\n" +
                        "\n" +
                        "Medications. If a medication is the cause of your hypoglycemia, your doctor will likely suggest changing or stopping the medication or adjusting the dosage.\n" +
                        "Tumor treatment. A tumor in your pancreas is treated by surgical removal of the tumor. In some cases, partial removal of the pancreas is necessary.");
                break;
            case "Osteoarthristis":
                abouttreatment.setText("Hot and cold compresses\n" +
                        "When it comes to pain, hot and cold compresses may be very beneficial. They don’t cause the long-term side effects that medications might. Hot compresses are helpful for joint stiffness, and cold compresses are best for joint pain.\n" +
                        "\n" +
                        "The Arthritis Foundation says that heat helps soothe stiff joints and relax muscles, while cold helps numb sharp pain and reduce inflammation.\n" +
                        "\n" +
                        "Compresses can reduce muscle pain or spasms surrounding a joint. Making a compress can be as simple as using a warm or cold towel.\n" +
                        "\n" +
                        "Find out how to make a cold compress.\n" +
                        "\n" +
                        "Epsom salt bath\n" +
                        "Epsom salt baths can provide all-over relief, especially for joint pain. The magnesium in Epsom salt may help with inflammation and pain. A 2017 reviewTrusted Source showed that soaking in an Epsom salt bath for a prolonged time may increase your magnesium levels.\n" +
                        "\n" +
                        "You can buy Epsom salt from a drugstore. According to the Epsom Salt Council, these baths are safe enough to take as often as you’d like for 30 minutes at a time. Use up to 3 cups of Epsom salt in a bath of warm water.\n" +
                        "\n" +
                        "Topical ointments and creams\n" +
                        "You may want to try topical versions as an alternative to oral over-the-counter (OTC) medications like acetaminophen and ibuprofen.\n" +
                        "\n" +
                        "These gels and creams may contain aspirin or other pain relievers to numb the pain. You can apply them directly to the affected joints. These products can work well for areas that are near the skin surface, such as your knees.\n" +
                        "\n" +
                        "An example of such a gel is diclofenac (Voltaren), a topical nonsteroidal anti-inflammatory drug (NSAID). According to a 2020 reviewTrusted Source, it provides a pain-relieving effect.\n" +
                        "\n" +
                        "Another topical home remedy is capsaicin. Capsaicin is a compound made from hot chili peppers. A 2014 reviewTrusted Source suggests it works best when you apply it to painful joints three to four times per day. To avoid accidental eye exposure, wash hands after each use.\n" +
                        "\n" +
                        "You may also want to try other ointments like Tiger Balm. Talk with your doctor before experimenting with any of these products.\n" +
                        "\n" +
                        "Read more about the best pain creams for arthritis.\n" +
                        "\n" +
                        "Support devices\n" +
                        "The Arthritis Foundation says that various types of assistive devices can offer added support without the need for medications. The exact devices you choose depend on the affected joints. According to a 2018 review, options include:\n" +
                        "\n" +
                        "braces\n" +
                        "canes\n" +
                        "grabbing or gripping tools\n" +
                        "knee taping (be sure to have your doctor or physical therapist show you first)\n" +
                        "shoe inserts\n" +
                        "Find out what’s the best brace for osteoarthritis.\n" +
                        "\n" +
                        "\n" +
                        "Herbal remedies\n" +
                        "Herbal remedies are increasing in popularity for conditions like osteoarthritis. Some people believe they may be safer since they have fewer side effects compared with traditional medications.\n" +
                        "\n" +
                        "Talk with your doctor about the following natural remedies. “Natural” herbal supplements can carry side effects and interact with medications you might take. Always be sure to buy supplements from a reputable source.\n" +
                        "\n" +
                        "Green tea\n" +
                        "Green tea contains polyphenols. A 2021 reviewTrusted Source suggests these compounds may help lower inflammation and the need for medications.\n" +
                        "\n" +
                        "A small 2018 studyTrusted Source on the use of green tea in treating knee osteoarthritis showed that the tea can help with joint function, but more research is needed to verify these results.\n" +
                        "\n" +
                        "Due to the risk of liver problems and side effects from concentrated amounts, it’s best to drink green tea in moderation.\n" +
                        "\n" +
                        "Ginger\n" +
                        "Oral ginger is also noted for reducing pain from osteoarthritis. According to a 2015 study, taking ginger long-term may even decrease the risk of osteoarthritis-related disability.\n" +
                        "\n" +
                        "Due to the risk of side effects, the National Center for Complementary and Integrative HealthTrusted Source recommends using ginger moderately as a spice instead of in supplement forms.\n" +
                        "\n" +
                        "Learn how to make ginger tea for the benefits.\n" +
                        "\n" +
                        "The biggest risk to ginger overdose is the withdrawal symptoms. Ginger can cause upset stomach, diarrhea, and heartburn. It may also interact with prescription medications, like warfarin, because it’s an anticoagulant, or blood thinner. Speak with your doctor before adding or increasing your consumption of ginger.\n" +
                        "\n" +
                        "Turmeric and curcumin\n" +
                        "Curcumin is the active compound in turmeric. It’s part of the ginger family but may help osteoarthritis in different ways. A 2016 studyTrusted Source on mice with osteoarthritis showed that curcumin may be able to slow disease progression and provide pain relief. However, a 2017 reviewTrusted Source of clinical studies on humans did not find the same conclusive results. More research is needed to verify the effect.\n" +
                        "\n" +
                        "If you want to try turmeric as a natural treatment, the Arthritis Foundation recommends using curcumin extract, as whole curcumin may be contaminated with lead. Take 500 milligrams twice daily. While turmeric is generally safe, it can cause nausea and may interact with blood thinners.\n" +
                        "\n" +
                        "Read more about adding turmeric to your diet.\n" +
                        "\n" +
                        "Lifestyle changes\n" +
                        "For more long-term relief, lifestyle changes are often effective. Regular exercise, a healthy diet, and weight maintenance can help improve joint health and function. Over time, the muscles stabilizing your joints will strengthen and protect against damage.\n" +
                        "\n" +
                        "Stay active\n" +
                        "Exercise can be difficult with painful joints. But staying active can reduce pain in the long run, and even strengthen muscles to prevent further joint damage. The Arthritis Foundation says exercise is “the most effective, non-drug treatment for reducing pain and improving movement in patients with osteoarthritis.”\n" +
                        "\n" +
                        "The best types of exercise for osteoarthritis use slight resistance, improve flexibility, offer an aerobic element, and are low impact. Options include:\n" +
                        "\n" +
                        "bike riding\n" +
                        "swimming\n" +
                        "tai chi\n" +
                        "walking\n" +
                        "yoga\n" +
                        "Talk with your doctor before starting any new exercises, especially if you haven’t been active before. The Arthritis Foundation recommends 150 minutes of aerobic exercise every week at a moderate intensity or 75 minutes at a higher intensity. You can also start with shorter periods of exercise and add time as you get stronger.\n" +
                        "\n" +
                        "For example, you could start walking for 10 minutes and gradually increase the speed and length of your walks. If you’re new to exercise, you may find slight pain after your workouts. This could mean you need to take 1 or 2 days off and then resume your workout regimen. Don’t quit exercising altogether.\n" +
                        "\n" +
                        "Eat osteoarthritis-friendly foods\n" +
                        "Eating a balanced diet can help you feel better and lose weight. Research shows that certain foods are especially beneficial for osteoarthritis. In particular, eating a Mediterranean diet may help you consume the right things and avoid foods that may make your symptoms worse.\n" +
                        "\n" +
                        "According to a small 2020 studyTrusted Source, vegetables like broccoli, spinach, lettuce, kale, and cabbage are rich in vitamin K and have anti-inflammatory properties. The Arthritis Foundation also says that while the evidence is not completely conclusive, consuming dairy products with calcium and vitamin D may promote joint and bone health. However, dairy also contains casein, which is an ingredient some people may need to avoid.\n" +
                        "\n" +
                        "In addition, good foods to eat include nuts and plant-based oils. An example from a 2018 reviewTrusted Source is olive oil. Another good food is fish rich in omega-3 fatty acids, such as tuna, salmon, and mackerel.\n" +
                        "\n" +
                        "On the flip side, the Arthritis Foundation says that certain foods can aggravate osteoarthritis symptoms by increasing body inflammation. These foods include:\n" +
                        "\n" +
                        "alcohol\n" +
                        "aspartame, an artificial sweetener\n" +
                        "salt\n" +
                        "saturated and trans fat\n" +
                        "omega-6 fatty acids\n" +
                        "sugar\n" +
                        "refined carbohydrates like white bread, pasta, or rice\n" +
                        "foods with monosodium glutamate (MSG)\n" +
                        "gluten\n" +
                        "casein\n" +
                        "Maintain a moderate weight\n" +
                        "According to the Arthritis Foundation, weight loss can go a long way in alleviating joint pain and preventing osteoarthritis.\n" +
                        "\n" +
                        "Maintaining a moderate weight can help you keep excessive pressure away from your joints, as well as help reduce pain and inflammation.\n" +
                        "\n" +
                        "This may be particularly helpful for people with symptoms in their knees and hips, as these joints bear a lot of weight.\n" +
                        "\n" +
                        "When to contact your doctor\n" +
                        "Osteoarthritis is a chronic, or lifelong, condition with no cure. Managing your condition and symptoms can go a long way in stopping further damage to your joints. Lifestyle changes and home and natural remedies can complement your treatment plan. They may even provide extra relief.\n" +
                        "\n" +
                        "While such changes can make a big difference, it’s important to know when you need to contact your doctor. You might need to make an appointment if you have a flare-up, your symptoms get worse, or your current treatment plan isn’t helping. Your doctor should check your joint pain and stiffness for potential damage.");
                break;
            case "Arthritis":
                abouttreatment.setText("1. Manage your weight\n" +
                        "Your weight can have a big impact on arthritis symptoms. Extra weight puts more pressure on your joints, especially your knees, hips, and feet.\n" +
                        "\n" +
                        "Guidelines from the American College of Rheumatology and Arthritis Foundation (ACR/AF) strongly recommend losing weight if you have OA and overweight or obesity.\n" +
                        "\n" +
                        "Your doctor can help you set a target weight and design a program to help you reach that target.\n" +
                        "\n" +
                        "Reducing the stress on your joints by losing weight can help:\n" +
                        "\n" +
                        "improve your mobility\n" +
                        "decrease pain\n" +
                        "prevent future damage to your joints\n" +
                        "2. Get enough exercise\n" +
                        "If you have arthritis, exercise can help you:\n" +
                        "\n" +
                        "manage your weight\n" +
                        "keep your joints flexible\n" +
                        "strengthen muscles around your joints, which offers more support\n" +
                        "Current guidelines strongly recommend starting an appropriate exercise program. Exercising with a trainer or another person may be especially beneficial, as it increases motivation.\n" +
                        "\n" +
                        "Good options include low-impact exercises, such as:\n" +
                        "\n" +
                        "walking\n" +
                        "cycling\n" +
                        "tai chi\n" +
                        "water activities\n" +
                        "swimming\n" +
                        "3. Use hot and cold therapy\n" +
                        "Heat and cold treatments can help relieve arthritis pain and inflammation.\n" +
                        "\n" +
                        "Heat treatments can include taking a long, warm shower or bath in the morning to help ease stiffness and using an electric blanket or moist heating pad to reduce discomfort overnight.\n" +
                        "Cold treatments can help relieve joint pain, swelling, and inflammation. Wrap a gel ice pack or a bag of frozen vegetables in a towel and apply it to painful joints for quick relief. Never apply ice directly to the skin.\n" +
                        "Capsaicin, which comes from chili peppers, is a component of some topical ointments and creams that you can buy over the counter. These products provide warmth that can soothe joint pain.\n" +
                        "\n" +
                        "4. Try acupuncture\n" +
                        "Acupuncture is an ancient Chinese medical treatment that involves inserting thin needles into specific points on your body. Practitioners say it works by rerouting energies and restoring balance in your body.\n" +
                        "\n" +
                        "Acupuncture may reduce arthritis pain, and the ACR/AF conditionally recommend it. While there’s not enough evidence to confirm its benefits, the risk of harm is considered low.\n" +
                        "\n" +
                        "Be sure to find a licensed and certified acupuncturist to carry out this treatment.\n" +
                        "\n" +
                        "5. Use meditation to cope with pain\n" +
                        "Meditation and relaxation techniques may help reduce the pain of arthritis by lowering stress and enabling you to cope with it better. Reducing stress may also help lower inflammation and pain.\n" +
                        "\n" +
                        "The ACR/AF recommend tai chi and yoga. These combine meditation, relaxation, and breathing techniques with low-impact exercise.\n" +
                        "\n" +
                        "According to the National Institutes of Health (NIH), studies have found that practicing mindfulness meditation is helpful for some people with RA.\n" +
                        "\n" +
                        "Anxiety, stress, and depression are all common complications of conditions that involve chronic pain, such as arthritis.\n" +
                        "\n" +
                        "Learn more about depression and arthritis.\n" +
                        "\n" +
                        "6. Follow a healthy diet\n" +
                        "A diet that’s rich in fresh fruits, vegetables, and whole foods can help boost your immune system and your overall health. There’s some evidence that dietary choices can affect people with both RA and OA.\n" +
                        "\n" +
                        "A plant-based diet provides antioxidants, which can help reduce inflammation by eliminating free radicals from the body.\n" +
                        "\n" +
                        "On the other hand, a diet rich in red meat, processed foods, saturated fat, and added sugar and salt may aggravate inflammation, which is a characteristic of arthritis.\n" +
                        "\n" +
                        "These foods can also contribute to other health conditions, including obesity, high cholesterol, high blood pressure, heart disease, and other complications, so they’re likely not beneficial for people with arthritis.\n" +
                        "\n" +
                        "Current OA guidelines do not recommend taking vitamin D or fish oil supplements as a treatment, but consuming foods containing these nutrients as part of a balanced diet may contribute to overall well-being.\n" +
                        "\n" +
                        "What should you eat to stay healthy with arthritis?\n" +
                        "\n" +
                        "Which foods should you avoid?\n" +
                        "\n" +
                        "7. Add turmeric to dishes\n" +
                        "Turmeric, the yellow spice common in Indian dishes, contains a chemical called curcumin. It has antioxidant and anti-inflammatory properties. Research suggests it may help reduce arthritis pain and inflammation.\n" +
                        "\n" +
                        "In an animal study that the National Center for Complementary and Integrative HealthTrusted Source cited, scientists gave turmeric to rats. Results showed that it reduced inflammation in their joints.\n" +
                        "\n" +
                        "More research is needed to show how turmeric works, but adding a small amount of this mild but tasty spice to your dinner is likely to be a safe option.\n" +
                        "\n" +
                        "Spice up your life by grabbing some online today.\n" +
                        "\n" +
                        "8. Get a massage\n" +
                        "Massage can provide an overall sense of well-being. It may also help manage joint pain and discomfort.\n" +
                        "\n" +
                        "The ACR/AF do not currently recommend massage as a treatment, as they say there’s not enough evidence to confirm that it works.\n" +
                        "\n" +
                        "They add, however, that massage is unlikely to pose a risk and may provide indirect benefits, such as reducing stress.\n" +
                        "\n" +
                        "Ask your doctor to recommend a massage therapist who has experience in treating people with arthritis. Alternatively, you could ask a physical therapist to teach you self-massage.\n" +
                        "\n" +
                        "9. Consider herbal supplements\n" +
                        "Many herbal supplements may reduce joint pain, although scientific research hasn’t confirmed that any specific herb or supplement can treat arthritis.\n" +
                        "\n" +
                        "Some of these herbs include:\n" +
                        "\n" +
                        "boswellia\n" +
                        "bromelain\n" +
                        "devil’s claw\n" +
                        "ginkgo\n" +
                        "stinging nettle\n" +
                        "thunder god vine");
                break;
            case "(vertigo) Paroymsal  Positional Vertigo":
                abouttreatment.setText("Epley maneuver\n" +
                        "Also called the “Canalith” repositioning maneuver, the Epley maneuver is the first go-to strategy for many people experiencing vertigo. ResearchTrusted Source indicates that the Epley maneuver is extremely effective for people with BPPV. You can perform the maneuver at home by following this simple procedure:\n" +
                        "\n" +
                        "Start by sitting upright on a flat surface, with a pillow behind you and with your legs outstretched.\n" +
                        "Turn your head 45 degrees to the right.\n" +
                        "With your head still titled, quickly recline with your head on the pillow. Stay in this position for at least 30 seconds.\n" +
                        "Slowly turn your head to the left, a full 90 degrees, without lifting your neck.\n" +
                        "Engage your whole body, turning it to the left so that you are completely on your left side.\n" +
                        "Slowly return to your original position, looking forward and sitting straight up.\n" +
                        "You may also have someone assist you with the Epley maneuver by guiding your head according to the steps outlined above. It can be repeated three times in a row, and you may feel dizzy during each movement.\n" +
                        "\n" +
                        "Semont-Toupet maneuver\n" +
                        "The Semont-Toupet maneuver is a similar set of movements that you can perform at home to treat vertigo. This maneuver is less well-known, but some studiesTrusted Source claim it is just as effective.Trusted Source The Semont-Toupet maneuver is very similar to the Epley Maneuver, but it requires less neck flexibility.\n" +
                        "\n" +
                        "Start by sitting upright on a flat surface, with a pillow behind you and with your legs outstretched.\n" +
                        "Lie down, turning to your right, and look to your left side, looking upward.\n" +
                        "Quickly sit up and turn to your left side, keeping your head facing to your left. You will now be looking down toward the ground.\n" +
                        "Slowly return to your original position, looking forward and sitting straight up.\n" +
                        "Brandt-Daroff exercise\n" +
                        "This exercise is most commonly recommended for people with vertigo to do at home, because it is simple to do it unsupervised. You shouldn’t perform the Brandt-Daroff exercise unless you are in a safe place and won’t be driving for a while, because it might provoke increased dizziness for a short period of time.\n" +
                        "\n" +
                        "Start by sitting on a flat surface, with your legs dangling as they would from a chair.\n" +
                        "Turn your head as far as you can to the left side, then lay your head and torso down on your right side. Your legs should not move. Stay here for at least 30 seconds.\n" +
                        "Sit up and turn your head back to the center position.\n" +
                        "Repeat the exercise on the opposite side by turning your head as far as you can to the right side, then laying down on your left side.\n" +
                        "You can do this exercise in a set of 5 repetitions and repeat it as often as 3 times a day, twice a week.\n" +
                        "\n" +
                        "Gingko biloba\n" +
                        "Ginkgo biloba has been studied for its effects on vertigo and found to be as effectiveTrusted Source as the leading prescription medication to treat vertigo. Gingko biloba extract can be purchased in liquid or capsule form. Taking 240 milligrams of ginkgo biloba each day should lessen your vertigo symptoms and make you feel more on-balance.\n" +
                        "\n" +
                        "Shop for ginkgo biloba supplements.\n" +
                        "\n" +
                        "\n" +
                        "Stress management\n" +
                        "Some conditions that cause vertigo, including Meniere’s disease, can be triggered by stress. Developing coping strategies to navigate stressful circumstances could decrease your episodes of vertigo. Practicing meditation and deep-breathing techniques are a good place to start. Long-term stress isn’t something you can simply breathe through, and often the causes of stress aren’t things that you can cut out of your life. Simply being aware of what is causing you stress might cut down on your vertigo symptoms.\n" +
                        "\n" +
                        "Yoga and tai chi\n" +
                        "YogaTrusted Source and tai chi are known to reduce stress while increasing flexibility and balance. Physical therapy performed in an outpatient setting trains your brain to compensate for the cause of your vertigo, and exercise you do at home can mimic this effect. Try simple yoga poses, such as Child’s Pose and Corpse Pose, when you’re feeling dizzy. Be cautious about anything that involves sudden bending forward, as that could make your symptoms temporarily feel stronger.\n" +
                        "\n" +
                        "Shop for yoga mats.\n" +
                        "\n" +
                        "Adequate amount of sleep\n" +
                        "Feelings of vertigo can be triggeredTrusted Source by sleep deprivation. If you’re experiencing vertigo for the first time, it might be a result of stress or lack of sleep. If you can stop what you’re doing and take a short nap, you may find that your feelings of vertigo have resolved themselves.\n" +
                        "\n" +
                        "Hydration\n" +
                        "Sometimes vertigo is caused by simple dehydration. Reducing your sodium intake may help. But the best way to stay hydrated is to simply drink plenty of water. Monitor your water intake and try to account for hot, humid conditions and sweaty situations that might make you lose extra fluids. Plan to drink extra water during times you tend to become dehydrated. You might find that simply being aware of how much water you’re drinking helps decrease vertigo episodes.\n" +
                        "\n" +
                        "Vitamin D\n" +
                        "If you suspect your vertigo is connected to something you aren’t getting in your diet, you could be right. A studyTrusted Source suggests that a lack of vitamin D can worsen symptoms for people that have BPPV, the most common cause of vertigo. A glass of fortified milk or orange juice, canned tuna, and even egg yolks will all give your levels of vitamin D a boost. Have your doctor check your vitamin D levels so you know if you need more in your diet or if you need a supplement.\n" +
                        "\n" +
                        "Shop for vitamin D supplements.\n" +
                        "\n" +
                        "Avoiding alcohol\n" +
                        "Beyond the dizziness you feel while drinking, alcohol can actually change the composition of the fluid in your inner ear, according to the Vestibular Disorders Association. Alcohol also dehydrates you. These things can affect your balance even when you’re sober. Cutting back on alcohol consumption, or even stopping completely, might help your vertigo symptoms.");
                break;
            case "Acne":
                abouttreatment.setText("1. Grape Cleanser\n" +
                        "vine of red grapes in bowl\n" +
                        "iStock\n" +
                        "Grapes are a refreshing snack, whether eaten plain as a snack, halved as a salad topping, or frozen as a healthy dessert. But grapes likely don’t come to mind when you think of acne treatments.\n" +
                        "\n" +
                        "Yet according to an article published in April 2016 in Advances in Dermatology and Allergology, resveratrol in the skin of red grapes may exhibit bactericidal activity against Cutibacterium acnes. formerly called Propionibacterium acnes, C. acnes is a bacteria in the sebaceous glands that contributes to acne. (2)\n" +
                        "\n" +
                        "So grab a few fresh grapes from your fridge, and you've got an easy facial cleanser. Cut two or three grapes in half and rub the flesh over your face and neck, says Fran E. Cook-Bolden, MD, a dermatologist and director of Skin Specialty Dermatology in New York City. Follow with a cool water rinse.\n" +
                        "\n" +
                        "2. Cucumber Face Mask\n" +
                        "slices of cucumber bowl of oatmeal diptych\n" +
                        "iStock (2)\n" +
                        "If you’ve ever been to a spa, you’ve likely put cucumber slices on your eyes to reduce puffiness — and they may not be a sham treatment.\n" +
                        "\n" +
                        "\n" +
                        "A past review suggests that cucumbers can have a soothing effect on the skin, reducing irritation, swelling, and pain. (3) Hence, they can potentially relieve inflammation specifically associated with acne.\n" +
                        "\n" +
                        "In the case of acne, “inflammation develops within the oil gland and follicle, leading to red, angry bumps in the skin,” explains Dr. Zeichner. When acne develops deep within the skin, “a cyst may develop and become filled with oil. If the cyst wall ruptures and the oil becomes exposed to the deeper skin layers, a robust inflammatory reaction often occurs,” he says.\n" +
                        "\n" +
                        "How can cucumbers help?\n" +
                        "\n" +
                        "“Cucumbers have skin soothing and hydrating benefits, so if your skin is inflamed from acne, cucumbers may offer a modest calming benefit,” notes Zeichner. But he warns that they’re not effective at treating the underlying pimples.\n" +
                        "\n" +
                        "Here’s how to reap the benefits: \"Make a paste by blending one small cucumber and 1 cup of oatmeal,\" says Dr. Cook-Bolden. Mix 1 teaspoon [tsp] of this paste with 1 tsp of yogurt and apply it to your face. Leave it on for 30 minutes, and then rinse.\n" +
                        "\n" +
                        "3. Cucumber Face Pack\n" +
                        "cucumber slices cubes of sugar diptych\n" +
                        "iStock (2)\n" +
                        "If you don’t have oatmeal or yogurt in your kitchen, use this cucumber remedy instead. This cooling, soothing mask will help smooth your skin, which can feel rough from acne. \"Mash one whole cucumber, strain the water, add 1 tablespoon of sugar, and mix well,\" says Cook-Bolden. \"Apply to your face and leave it on for 10 minutes; then wash with cold water.\"\n" +
                        "\n" +
                        "4. Simple Honey Mask\n" +
                        "dripping honey\n" +
                        "Edward Fury/Stocksy\n" +
                        "Honey has many healing properties. According to the Mayo Clinic, some people use it as a natural cough syrup and to relieve a sore throat. (4)\n" +
                        "\n" +
                        "According to an article published in August 2016 in the Central Asian Journal of Global Health, in vitro studies suggest that the antibacterial properties of honey may also promote healing of burns and wound infections, and inhibit the growth of C. acnes. (5)\n" +
                        "\n" +
                        "Before applying this mask, rinse your face with warm water, says Cook-Bolden. Then apply the honey and leave it on the skin for 30 minutes. Rinse the honey off with warm water.\n" +
                        "\n" +
                        "5. Yeast and Yogurt Mask for Oily Skin\n" +
                        "dry yeast on cutting boat plain yogurt in bowl diptych\n" +
                        "iStock (2)\n" +
                        "Although more research is needed, evidence published in April 2015 in the International Journal of Women’s Dermatology suggested that fermented dairy products like yogurt may promote skin health. Yogurt is also a probiotic, which has been shown to inhibit C. acnes. (6)\n" +
                        "\n" +
                        "To make the mask, combine 1 tsp of brewer's yeast with a little plain yogurt to create a thin mixture. Cook-Bolden says, \"Apply it thoroughly to all the oily areas and leave on for 15 to 20 minutes. Rinse with warm water; then use cold water to close the pores.\"\n" +
                        "\n" +
                        "6. Oatmeal Facial\n" +
                        "bowl of oatmeal\n" +
                        "Debbi Smirnoff/iStock\n" +
                        "The anti-inflammatory properties of oatmeal are naturally soothing and may relieve irritation caused by dermatological conditions like rashes, erythema, burns, itch, and eczema, according to research published in January 2015 in the Journal of Drugs and Dermatology. (7)\n" +
                        "\n" +
                        "Oatmeal has skin protecting, hydrating, and anti-inflammatory benefits, says Zeichner. “It’s not specifically effective in treating acne, but it can help soothe dry, inflamed skin. I commonly recommend oat-based moisturizers for my patients who use potentially irritating acne treatments,” he further notes.\n" +
                        "\n" +
                        "Mix together 2 tsp of oatmeal, 1 tsp of baking soda, and enough water to form a paste. Smooth the paste all over your face and gently rub it in. Rinse thoroughly afterward.\n" +
                        "\n" +
                        "7. Turmeric Facial Mask\n" +
                        "turmeric in a bowl\n" +
                        "Nataša Mandić/Stocksy\n" +
                        "For beautiful skin, brides in India traditionally use a turmeric mask before their wedding, says Cook-Bolden. This is likely due to the spice’s anti-inflammatory, antimicrobial, and antioxidant properties, and its ability to significantly improve the severity of skin conditions like acne, alopecia, atopic dermatitis, oral lichen planus, pruritus, and psoriasis, according to a review published in August 2016 in Phytotherapy Research. (8)\n" +
                        "\n" +
                        "Ingredients for this acne remedy are available at spice markets and ethnic food stores.\n" +
                        "\n" +
                        "Mix 1/2 cup of chickpea flour and 2 tsp each turmeric powder, sandalwood powder, and ghee (clarified butter) or almond oil, and then combine them with enough water to make a paste. \"Apply and leave on for 5 to 10 minutes,\" says Cook-Bolden. \"Rub with pressure with both palms and fingers to remove all the paste.\" Rinse well with water.\n" +
                        "\n" +
                        "A Final Word on Home Remedies for Acne\n" +
                        "Homemade facial masks can rejuvenate your skin and help clear acne — and all of these recipes use common ingredients found in your pantry and refrigerator. Try one or more of these remedies to treat pimple-prone skin and see how your skin responds.\n" +
                        "\n" +
                        "If at-home treatments don’t help after two to four weeks, touch base with a dermatologist for help, says Zeichner. “Unfortunately, delays in treatment increase the risk of developing stains in the skin, or in some cases, permanent scars,” he warns.");
                break;
            case "Urinary tract infection":
                abouttreatment.setText("1. Drink plenty of fluids\n" +
                        "Hydration status has been linked to the risk of urinary tract infection (6Trusted Source).\n" +
                        "\n" +
                        "This is because regular urination can help flush bacteria from the urinary tract to prevent infection.\n" +
                        "\n" +
                        "A study examined nursing home residents and administered a drinking schedule to participants to increase their fluid intake, which decreased UTIs requiring antibodies by 56 percent (6Trusted Source).\n" +
                        "\n" +
                        "An older 2003 study looked at 141 girls and showed that low fluid intake and infrequent urination were both linked to recurrent UTIs (7Trusted Source).\n" +
                        "\n" +
                        "In a randomized control trial, 140 premenopausal women prone to UTIs participated in a 12-month study to test if a higher fluid intake would decrease their risk of recurrent cystitis and in turn their risk of developing a UTI. They found that an increase in fluid intake led to a decrease in UTI frequency (8Trusted Source).\n" +
                        "\n" +
                        "To stay hydrated and meet your fluid needs, it’s best to drink water throughout the day and always when you’re thirsty.\n" +
                        "\n" +
                        "SUMMARY\n" +
                        "Drinking plenty of liquids can decrease the risk of UTIs by making you pee more, which helps remove bacteria from the urinary tract.\n" +
                        "\n" +
                        "2. Increase vitamin C intake\n" +
                        "Some evidence shows that increasing your intake of vitamin C could protect against urinary tract infections.\n" +
                        "\n" +
                        "Vitamin C is thought to work by increasing the acidity of the urine, thereby killing off the bacteria that cause infection (9Trusted Source).\n" +
                        "\n" +
                        "An older 2007 study of UTIs in pregnant women looked at the effects of taking 100 mg of vitamin C every day (10Trusted Source).\n" +
                        "\n" +
                        "The study found that vitamin C had a protective effect, cutting the risk of UTIs by more than half in those taking vitamin C, compared with the control group (10Trusted Source).\n" +
                        "\n" +
                        "Fruits and vegetables are especially high in vitamin C and are a good way to increase your intake.\n" +
                        "\n" +
                        "Red peppers, oranges, grapefruit, and kiwifruit all contain the full recommended amount of vitamin C in just one serving (11Trusted Source).\n" +
                        "\n" +
                        "Despite these studies, there is still more research needed to prove the effectiveness of vitamin C for reducing UTIs. (12Trusted Source).\n" +
                        "\n" +
                        "SUMMARY\n" +
                        "Increasing vitamin C intake may decrease the risk of UTIs by making the urine more acidic, thus killing off infection-causing bacteria.\n" +
                        "\n" +
                        "3. Drink unsweetened cranberry juice\n" +
                        "Drinking unsweetened cranberry juice is one of the most well-known natural remedies for urinary tract infections.\n" +
                        "\n" +
                        "Cranberries work by preventing bacteria from adhering to the urinary tract, thus preventing infection (13Trusted Source, 14Trusted Source).\n" +
                        "\n" +
                        "In a 2016 study, women with recent histories of UTIs drank an 8-ounce (240-mL) serving of cranberry juice every day for 24 weeks. Those who drank cranberry juice had fewer UTI episodes than the control group (15Trusted Source).\n" +
                        "\n" +
                        "Another study showed that consuming cranberry products may lower the number of UTIs in a year, especially for women who have recurrent UTIs (16Trusted Source).\n" +
                        "\n" +
                        "A 2015 study showed that treatment with cranberry juice capsules equivalent to two 8-ounce servings of cranberry juice could cut the risk of UTIs in half (17Trusted Source).\n" +
                        "\n" +
                        "However, some other studies suggest that cranberry juice may not be as effective in the prevention of UTIs.\n" +
                        "\n" +
                        "One 2012 review looked at 24 studies with a total of 4,473 participants. Though some smaller studies did find that cranberry products could reduce UTI frequency, other larger studies found no benefit (18Trusted Source).\n" +
                        "\n" +
                        "Although the evidence is mixed, cranberry juice may help reduce the risk of urinary tract infections.\n" +
                        "\n" +
                        "Keep in mind that these benefits only apply to unsweetened cranberry juice, rather than sweetened commercial brands.\n" +
                        "\n" +
                        "SUMMARY\n" +
                        "Some studies show that cranberries could help reduce the risk of UTIs by preventing bacteria from adhering to the urinary tract.\n" +
                        "\n" +
                        "\n" +
                        "4. Take a probiotic\n" +
                        "Probiotics are beneficial microorganisms that are consumed through food or supplements. They can promote a healthy balance of bacteria in your gut.\n" +
                        "\n" +
                        "Probiotics are available in supplement form or can be found in fermented foods, such as kefir, kimchi, kombucha, and probiotic yogurt.\n" +
                        "\n" +
                        "The use of probiotics has been linked to many things, from improved digestive health to enhanced immune function (19Trusted Source, 20Trusted Source).\n" +
                        "\n" +
                        "Some studies also show that certain strains of probiotics may decrease the risk of UTIs.\n" +
                        "\n" +
                        "One study found that Lactobacillus, a common probiotic strain, helped prevent UTIs in adult women (21Trusted Source).\n" +
                        "\n" +
                        "Another study found that taking both probiotics and antibiotics was more effective at preventing recurrent UTIs than using antibiotics alone (22Trusted Source).\n" +
                        "\n" +
                        "Antibiotics, the main line of defense against UTIs, can cause disturbances in levels of gut bacteria. Probiotics may be beneficial in restoring gut bacteria after antibiotic treatment (23Trusted Source).\n" +
                        "\n" +
                        "Studies have shown that probiotics can increase levels of good gut bacteria and reduce side effects associated with antibiotic use (24Trusted Source, 25Trusted Source).\n" +
                        "\n" +
                        "SUMMARY\n" +
                        "Probiotics could help prevent UTIs when used alone or in combination with antibiotics.\n" +
                        "\n" +
                        "UTI supplement options\n" +
                        "Read our full review of Uqora, a company that focuses on developing natural supplements for UTI prevention.\n" +
                        "\n" +
                        "5. Practice these healthy habits\n" +
                        "Preventing urinary tract infections starts with practicing a few good bathroom and hygiene habits.\n" +
                        "\n" +
                        "First, it’s important not to hold urine for too long. This can lead to a buildup of bacteria, resulting in infection (26Trusted Source).\n" +
                        "\n" +
                        "Peeing after sexual intercourse can also reduce the risk of UTIs by preventing the spread of bacteria (11Trusted Source).\n" +
                        "\n" +
                        "Additionally, those who are prone to UTIs should avoid using spermicide, as it has been linked to an increase in UTIs (27Trusted Source).\n" +
                        "\n" +
                        "Finally, when you use the toilet, make sure you wipe front to back. Wiping from back to front can cause bacteria to spread to the urinary tract and is associated with an increased risk of UTIs (28Trusted Source).\n" +
                        "\n" +
                        "SUMMARY\n" +
                        "Urinating frequently and after sexual intercourse can reduce the risk of UTI. Spermicide use and wiping from back to front may increase the risk of UTI.");
                break;
            case "Psoriasis":
                abouttreatment.setText("1. Take dietary supplements\n" +
                        "Dietary supplements may help ease psoriasis symptoms from the inside.\n" +
                        "\n" +
                        "Fish oil, vitamin D, milk thistle, aloe vera, Oregon grape, and evening primrose oil have all been reported to help ease mild symptoms of psoriasis, according to the National Psoriasis Foundation.\n" +
                        "\n" +
                        "Check with your doctor before taking supplements to make sure they don’t interfere with other health conditions you may have or medications you’re taking.\n" +
                        "\n" +
                        "\n" +
                        "2. Prevent dry skin\n" +
                        "Use a humidifier to keep the air in your home or office moist. This can help prevent dry skin before it starts.\n" +
                        "\n" +
                        "Moisturizers for sensitive skin can keep your skin supple and preventing plaques from forming.\n" +
                        "\n" +
                        "3. Try aloe\n" +
                        "Aloe vera has been shown in some cases to reduce redness and irritation caused by psoriasis. A 2010 studyTrusted Source found aloe vera gel cream to be slightly more effective in improving psoriasis symptoms compared to 0.1 percent triamcinolone acetonide, a steroid cream used to treat psoriasis.\n" +
                        "\n" +
                        "More research is needed to show for sure if aloe vera can improve symptoms of psoriasis. However, the risk of trying aloe vera gels or creams is low, so it may be worth a try.\n" +
                        "\n" +
                        "4. Avoid fragrances\n" +
                        "Most soaps and perfumes have dyes and other chemicals in them that may irritate your skin. They can make you smell great, but they also can inflame psoriasis.\n" +
                        "\n" +
                        "Avoid such products when you can, or choose those with “sensitive skin” labels.\n" +
                        "\n" +
                        "5. Eat healthfully\n" +
                        "Diet may play a role in managing psoriasis.\n" +
                        "\n" +
                        "Eliminating red meat, saturated fats, refined sugars, carbohydrates, and alcohol may help reduce flare-ups triggered by such foods.\n" +
                        "\n" +
                        "Cold water fish, seeds, nuts, and omega-3 fatty acids are known for their ability to reduce inflammation. This can be helpful for managing psoriasis symptoms.\n" +
                        "\n" +
                        "Olive oil may also have soothing benefits when applied topically to the skin. Try massaging a few tablespoons on your scalp to help loosen troublesome plaques during your next shower.\n" +
                        "\n" +
                        "Apple cider vinegar has also been found to be a good detoxifier for the body. You can drink it or apply it directly to plaques on the skin with a wash cloth.\n" +
                        "\n" +
                        "6. Soak your body\n" +
                        "A lukewarm bath with Epsom salt, mineral oil, milk, or olive oil can soothe the itching and infiltrate scales and plaques. Oatmeal baths can also be very helpful and soothing for plaque psoriasis.\n" +
                        "\n" +
                        "Be sure that the water is not hot. Hot water can cause more irritation.\n" +
                        "\n" +
                        "Moisturize immediately after your bath for double benefits.\n" +
                        "\n" +
                        "7. Get some rays\n" +
                        "Light therapy involves exposing your skin to ultraviolet light under the supervision of a doctor.\n" +
                        "\n" +
                        "Ultraviolet light can help slow the growth of skin cells triggered by psoriasis. This therapy often requires consistent and frequent sessions. Sitting in the sun for 10 to 15 minutes can also help reduce plaques.\n" +
                        "\n" +
                        "Tanning beds aren’t a good method of achieving light therapy. Too much sunlight can actually worsen psoriasis.\n" +
                        "\n" +
                        "Light therapy should always be done under the supervision of a doctor.\n" +
                        "\n" +
                        "8. Reduce stress\n" +
                        "Any chronic condition like psoriasis can be a source of stress, which in turn can worsen psoriasis symptoms.\n" +
                        "\n" +
                        "In addition to reducing stress whenever possible, consider incorporating stress-reducing practices such as yoga and meditation.\n" +
                        "\n" +
                        "9. Avoid alcohol\n" +
                        "Alcohol is a trigger for many people who have psoriasis.\n" +
                        "\n" +
                        "A study in 2015 found an increased risk of psoriasis among women who drank nonlight beer. Those who drank at least five nonlight beers per week were nearly twice as likely to develop psoriasis compared to women who didn’t drink.\n" +
                        "\n" +
                        "10. Try turmeric\n" +
                        "Herbs are commonly used to treat many conditions.\n" +
                        "\n" +
                        "Turmeric has been found to help minimize psoriasis flare-ups. It can be taken in pill or supplement form, or sprinkled on your food.\n" +
                        "\n" +
                        "Talk to your doctor about the potential benefits for you.\n" +
                        "\n" +
                        "11. Quitting smoking\n" +
                        "Avoid tobacco. Smoking may increase your risk of psoriasis.\n" +
                        "\n" +
                        "If you already have psoriasis, it can make your symptoms more severe.\n" +
                        "\n" +
                        "12. Maintain a healthy weight\n" +
                        "Being overweight or obese puts you at a greater risk of developing psoriasis. Obesity is also associated with more severe psoriasis symptoms. StudiesTrusted Source have found that losing weight can help improve these symptoms.\n" +
                        "\n" +
                        "Here are some tips for losing weight:\n" +
                        "\n" +
                        "exercise on a regular basis\n" +
                        "cut back on refined carbs\n" +
                        "eat plenty of vegetables and protein");
                break;
            case "Impetigo":
                abouttreatment.setText("1. Aloe vera (Aloe barbadensis)\n" +
                        "This African lily plant is a common ingredient for moisturizing skin products. The benefits of aloe vera could also apply to skin infections such as impetigo.\n" +
                        "\n" +
                        "A 2015 study tested aloe extract in a cream alongside neem oil. Results showed activity against Staphylococcus aureus as an antimicrobial when tested in a lab. This is a common bacteria strain that causes impetigo.\n" +
                        "\n" +
                        "Aloe may also counter the dryness and itching of impetigo.\n" +
                        "\n" +
                        "To use this remedy: Applying aloe gel directly from an aloe plant leaf to the skin works best. You can also try an ointment containing a high amount of aloe extract.\n" +
                        "\n" +
                        "2. Chamomile (Matricaria chamomilla/Chamaemelum nobile)\n" +
                        "Chamomile can be found in various skin products. It’s used to moisturize the skin and reduce inflammationTrusted Source. A 2011 reviewTrusted Source discussed its use against Staphylococcus, among other medicinal benefits.\n" +
                        "\n" +
                        "A 2014 study showed that chamomile could directly fight skin infections on animals. However, currently there’s no scientific evidence that chamomile helps treat skin infections in humans.\n" +
                        "\n" +
                        "To use this remedy: Make chamomile tea and use it as a skin wash. Or apply a used, cooled chamomile tea bag directly on sores.\n" +
                        "\n" +
                        "3. Garlic (Allium sativum)\n" +
                        "Garlic has historically been used to treat bacterial, viral, and fungal infections.\n" +
                        "\n" +
                        "Garlic extracts may suppress both bacteria strains that cause impetigo. One 2011 study showed it had some effectiveness in the lab against Staphylococcus. Another study conducted that year mentioned its effectiveness for Streptococcus strains.\n" +
                        "\n" +
                        "To use this remedy: Place the cut side of a slice of garlic directly on impetigo sores. This may sting a little. You can also press garlic cloves, and then apply topically. Garlic is also great to incorporate into your diet.\n" +
                        "\n" +
                        "Avoid using garlic on young children, as it may cause skin irritation.\n" +
                        "\n" +
                        "4. Ginger (Zingiber officinale)\n" +
                        "Ginger is another root with a long history. It’s a seasoning that has health benefits.\n" +
                        "\n" +
                        "Recently, studies have explored its antimicrobial properties. A 2012 study found that some of the components of ginger worked against Staphylococcus.\n" +
                        "\n" +
                        "To use this remedy: Place a slice of ginger, cut side down, on impetigo sores. It might sting a little. You can also juice ginger root and make a poultice from the juice, applying it topically. Incorporating ginger into your diet is another option.\n" +
                        "\n" +
                        "Avoid using ginger on young children, as it may cause skin irritation.\n" +
                        "\n" +
                        "5. Grapefruit seed (Citrus x paradisi)\n" +
                        "Grapefruit seed may help manage impetigo. A 2011 studyTrusted Source of grapefruit peel extract showed it had antimicrobial activity against Staphylococcus.\n" +
                        "\n" +
                        "To use this remedy: Grapefruit seed is available in liquid extract or tincture form. Dilute it with water and then apply the mixture topically to impetigo sores — undiluted alcoholic extracts can cause burning sensations on open wounds.\n" +
                        "\n" +
                        "6. Eucalyptus (Eucalyptus globulus)\n" +
                        "Eucalyptus is another alternative herbal skin treatment. It’s available in essential oil form. A 2014 study on rats showed it had antimicrobial properties against Staphylococcus. A 2016 lab study found it had inhibitory bioactivity effects on Streptococcus pyogenes.\n" +
                        "\n" +
                        "To use this remedy: Eucalyptus oil should only be used topically. This essential oil has been shown to be toxicTrusted Source, so ingesting it may be dangerous. To use, dilute a few drops of eucalyptus essential oil in water (two to three drops per ounce). Apply this mixture as a topical wash on impetigo sores.\n" +
                        "\n" +
                        "Topical use of properly diluted eucalyptus essential oil is generally safeTrusted Source. Some incidences of contact dermatitis have been reported, but they are rare.\n" +
                        "\n" +
                        "Avoid using eucalyptus oil on very young children, as it may cause dermatitis or skin irritation.\n" +
                        "\n" +
                        "7. Neem (Azadiractha indica)\n" +
                        "Neem is an Indian tree closely related to mahogany. Oil extracted from its bark is a popular alternative skin remedy.\n" +
                        "\n" +
                        "Neem is usually used for insect-related skin conditions like those that can result from lice or flea infestation. It also appears to be effective against certain bacteria, including strains that cause impetigo.\n" +
                        "\n" +
                        "One 2011 study showed it had activity against Staphylococcus bacteria. A 2013 study showed similar results against the two strains of bacteria that cause impetigo.\n" +
                        "\n" +
                        "To use this remedy: Follow the label directions provided with a neem oil product.\n" +
                        "\n" +
                        "8. Honey\n" +
                        "A delectable sweet, honey has long been used for medicinal purposes. For example, it has traditionally served as an antibacterial. Today, there is scientific support for this health benefit.\n" +
                        "\n" +
                        "A 2016 studyTrusted Source noted honey’s antimicrobial activity, so it’s possible that honey might be an antimicrobial for skin conditions, including impetigo. However, this hasn’t been demonstrated in human studies.\n" +
                        "\n" +
                        "Another 2012 lab study showed it combated Staphylococcus and Streptococcus bacteria quite well.\n" +
                        "\n" +
                        "To use this remedy: Manuka honey and raw honey are two of the most effective choices. Apply either type of honey directly to impetigo sores, and let it sit for 20 minutes. Rinse with warm water.\n" +
                        "\n" +
                        "9. Tea tree (Melaleuca alternifolia)\n" +
                        "Today, tea tree is one of the most widely used alternative natural skin treatments.\n" +
                        "\n" +
                        "This includes effectiveness in treating impetigo. In fact, impetigo was named one of many bacterial skin conditions it has been proposed to treat in a major 2017 dissertation review.\n" +
                        "\n" +
                        "To use this remedy: Tea tree is widely available as an essential oil. Dilute a few drops in water (two to three drops per ounce), and apply the solution as a topical wash on impetigo sores.\n" +
                        "\n" +
                        "Avoid using tea tree oil on young children, as it may cause dermatitis or skin irritation.\n" +
                        "\n" +
                        "10. Turmeric (Curcuma longa)\n" +
                        "Turmeric is best known as an Asian herbal spice. It also has a history as an anti-inflammatory remedy. Additionally, turmeric boasts antimicrobial properties, even against bacteria that cause impetigo.\n" +
                        "\n" +
                        "One 2016 study found that turmeric could fight Staphylococcus and Streptococcus better than certain herbs.\n" +
                        "\n" +
                        "To use this remedy: Try applying a turmeric poultice directly to impetigo sores. You can do this by mixing water with turmeric powder to make a paste.");
                break;
        }
        String article_to_read = abouttreatment.getText().toString();
        toSpeech = new TextToSpeech(getActivity().getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i!=TextToSpeech.ERROR){
                    toSpeech.setLanguage(Locale.ENGLISH);
                }
            }
        });
        texttospeechbutton_treatment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toSpeech.speak(article_to_read,TextToSpeech.QUEUE_FLUSH,null);
                texttospeechbutton_treatment.setVisibility(View.GONE);
                stoptexttospeech_treatment.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(), "Starting", Toast.LENGTH_SHORT).show();
            }
        });

        stoptexttospeech_treatment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toSpeech.stop();
                texttospeechbutton_treatment.setVisibility(View.VISIBLE);
                stoptexttospeech_treatment.setVisibility(View.GONE);
            }
        });
        return view;
    }
}