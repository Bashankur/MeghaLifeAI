package com.meghalife.app.language

import com.meghalife.app.data.AppLanguage

object OfflineTranslator {

    private val translations = mapOf(

        /* ───────── GENERAL / APP ───────── */
        "Welcome to MeghaLifeAI" to mapOf(
            AppLanguage.HINDI to "मेघालाइफएआई में आपका स्वागत है",
            AppLanguage.KHASI to "Pdiang burom sha MeghaLifeAI"
        ),
        "Choose how you want to use the app." to mapOf(
            AppLanguage.HINDI to "ऐप का उपयोग कैसे करना है चुनें",
            AppLanguage.KHASI to "Jied kumno phi kwah ban pyndonkam ïa ka app"
        ),
        "Choose Your Language" to mapOf(
            AppLanguage.HINDI to "अपनी भाषा चुनें",
            AppLanguage.KHASI to "Jied ia ka ktien"
        ),
        "Change Language" to mapOf(
            AppLanguage.HINDI to "भाषा बदलें",
            AppLanguage.KHASI to "Pynkylla ia ka ktien"
        ),

        /* ───────── MODES ───────── */
        "Resident Mode" to mapOf(
            AppLanguage.HINDI to "निवासी मोड",
            AppLanguage.KHASI to "Resident Mode"
        ),
        "Tourist Mode" to mapOf(
            AppLanguage.HINDI to "पर्यटक मोड",
            AppLanguage.KHASI to "Tourist Mode"
        ),
        "Driver Mode" to mapOf(
            AppLanguage.HINDI to "ड्राइवर मोड",
            AppLanguage.KHASI to "Driver Mode"
        ),
        "Continue as Resident" to mapOf(
            AppLanguage.HINDI to "निवासी के रूप में जारी रखें",
            AppLanguage.KHASI to "Leit kum u Resident"
        ),
        "Continue as Tourist" to mapOf(
            AppLanguage.HINDI to "पर्यटक के रूप में जारी रखें",
            AppLanguage.KHASI to "Leit kum u Tourist"
        ),
        "Continue as Driver" to mapOf(
            AppLanguage.HINDI to "ड्राइवर के रूप में जारी रखें",
            AppLanguage.KHASI to "Leit kum u Driver"
        ),

        /* ───────── BOTTOM NAV / HOME ───────── */
        "Explore" to mapOf(
            AppLanguage.HINDI to "घूमें",
            AppLanguage.KHASI to "Jngoh"
        ),
        "Transport" to mapOf(
            AppLanguage.HINDI to "परिवहन",
            AppLanguage.KHASI to "Ki kali"
        ),
        "Safety" to mapOf(
            AppLanguage.HINDI to "सुरक्षा",
            AppLanguage.KHASI to "Jingpynkhreh"
        ),
        "Profile" to mapOf(
            AppLanguage.HINDI to "प्रोफ़ाइल",
            AppLanguage.KHASI to "Profile"
        ),
        "Travel" to mapOf(
            AppLanguage.HINDI to "यात्रा",
            AppLanguage.KHASI to "Jingleit"
        ),

        /* ───────── TRANSPORT ───────── */
        "Track buses and plan your daily travel" to mapOf(
            AppLanguage.HINDI to "बस ट्रैक करें और दैनिक यात्रा की योजना बनाएं",
            AppLanguage.KHASI to "Peit ia ki bus bad thmu ia ka jingleit"
        ),
        "Live Bus Map" to mapOf(
            AppLanguage.HINDI to "लाइव बस मानचित्र",
            AppLanguage.KHASI to "Live Bus Map"
        ),
        "Running Early" to mapOf(
            AppLanguage.HINDI to "समय से पहले",
            AppLanguage.KHASI to "Ha shuwa ka por"
        ),
        "On Time" to mapOf(
            AppLanguage.HINDI to "समय पर",
            AppLanguage.KHASI to "Ha ka por"
        ),
        "Delayed" to mapOf(
            AppLanguage.HINDI to "देरी",
            AppLanguage.KHASI to "Kheiñ por"
        ),
        "Distance" to mapOf(
            AppLanguage.HINDI to "दूरी",
            AppLanguage.KHASI to "Jngai"
        ),
        "min" to mapOf(
            AppLanguage.HINDI to "मिनट",
            AppLanguage.KHASI to "minit"
        ),
        "km" to mapOf(
            AppLanguage.HINDI to "कि.मी.",
            AppLanguage.KHASI to "km"
        ),
        "Navigate" to mapOf(
            AppLanguage.HINDI to "मार्गदर्शन",
            AppLanguage.KHASI to "Pynleit"
        ),

        /* ───────── TOURIST / AI ───────── */
        "AI Recommended" to mapOf(
            AppLanguage.HINDI to "एआई द्वारा अनुशंसित",
            AppLanguage.KHASI to "AI ba jingïarap"
        ),
        "Travel Advisory" to mapOf(
            AppLanguage.HINDI to "यात्रा सलाह",
            AppLanguage.KHASI to "Ka jingïathuh jingïaid"
        ),
        "Risk Level" to mapOf(
            AppLanguage.HINDI to "जोखिम स्तर",
            AppLanguage.KHASI to "Ka jingïapher jingma"
        ),
        "Low travel risk. Conditions are suitable for travel." to mapOf(
            AppLanguage.HINDI to "कम जोखिम। यात्रा के लिए अनुकूल स्थिति।",
            AppLanguage.KHASI to "Khyndiat ka jingma. Ka jingïaid ka bha."
        ),
        "Moderate risk. Travel is possible but caution is advised." to mapOf(
            AppLanguage.HINDI to "मध्यम जोखिम। सावधानी आवश्यक।",
            AppLanguage.KHASI to "Ka jingma ka jylla. Donkam jingïapher."
        ),
        "High risk due to seasonal weather, terrain, or road conditions." to mapOf(
            AppLanguage.HINDI to "मौसम या सड़क के कारण उच्च जोखिम।",
            AppLanguage.KHASI to "Bun ka jingma namar ka suiñbneng lane surok."
        ),

        /* ───────── SAFETY ───────── */
        "Emergency" to mapOf(
            AppLanguage.HINDI to "आपातकाल",
            AppLanguage.KHASI to "Jingjia jingïarap"
        ),
        "SOS, nearby police stations, and hospitals." to mapOf(
            AppLanguage.HINDI to "एसओएस, नज़दीकी पुलिस स्टेशन और अस्पताल।",
            AppLanguage.KHASI to "SOS, ki pulit bad hospital ba jan."
        ),

        /* ───────── PROFILE ───────── */
        "Switch Mode" to mapOf(
            AppLanguage.HINDI to "मोड बदलें",
            AppLanguage.KHASI to "Pynkylla ia ka mode"
        ),
        "About App" to mapOf(
            AppLanguage.HINDI to "ऐप के बारे में",
            AppLanguage.KHASI to "Shaphang ka app"
        ),
        "Version" to mapOf(
            AppLanguage.HINDI to "संस्करण",
            AppLanguage.KHASI to "Version"
        ),
        "Share location via" to mapOf(
            AppLanguage.HINDI to "संस्करण",
            AppLanguage.KHASI to "Version"
        )
    )

    fun translate(text: String, language: AppLanguage): String {
        if (language == AppLanguage.ENGLISH) return text
        return translations[text]?.get(language) ?: text
    }
}
