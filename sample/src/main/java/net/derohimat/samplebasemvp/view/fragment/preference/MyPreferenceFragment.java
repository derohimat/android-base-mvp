package net.derohimat.samplebasemvp.view.fragment.preference;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import net.derohimat.samplebasemvp.R;

public class MyPreferenceFragment extends PreferenceFragment {

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);

        Preference version = findPreference("version");
        try {
            String versionName = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0).versionName;
            version.setSummary(versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        Preference buttonfeedback = findPreference(getString(R.string.send_feedback));
        buttonfeedback.setOnPreferenceClickListener(preference -> {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "your_email@gmail.com", null));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, this.getString(R.string.app_name) + " Feedback");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Write your feedback here...");
            startActivity(Intent.createChooser(emailIntent, "Send email..."));

            return true;
        });
    }

}