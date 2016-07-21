package com.codedleaf.sylveryte.myemployeeattendanceregister;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.UUID;

/**
 * Created by sylveryte on 19/7/16.
 */
public class SiteAdditionDialogFragment extends DialogFragment {

    private static String ARGS_CODE="siteargcode";

    private EditText mEditText_siteName;
    private EditText mEditText_description;
    private Button mAddButton;

    private Site mSite;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View view= LayoutInflater.from(getActivity())
                .inflate(R.layout.site_addition_fragment,null,false);

        mEditText_siteName=(EditText)view.findViewById(R.id.editText_site_name);
        mEditText_description=(EditText)view.findViewById(R.id.editText_site_description);
        mAddButton = (Button)view.findViewById(R.id.button_add_site);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDesignation(mSite);
                getDialog().dismiss();
            }
        });

        Bundle args=getArguments();
        //differentiate between edit and add calls
        if (args!=null)
        {
            UUID uuid=(UUID)args.getSerializable(ARGS_CODE);

            mSite=SitesLab.getInstanceOf(getActivity()).getSiteById(uuid);
            updateData();
        }

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .create();
    }

    private void updateData()
    {
        mEditText_siteName.setText(mSite.getTitle());
        mEditText_description.setText(mSite.getDescription());
    }



    public void saveDesignation(Site site)
    {
        if (site==null)
        {
            site=new Site();
            SitesLab.getInstanceOf(getActivity()).addSite(site);
        }

        site.setTitle(mEditText_siteName.getText().toString());
        site.setDescription(mEditText_description.getText().toString());

        site.updateMyDB(getActivity());
    }

    public static SiteAdditionDialogFragment getSiteFrag(@Nullable UUID uuid)
    {
        if (uuid==null)
            return new SiteAdditionDialogFragment();
        else
        {
            SiteAdditionDialogFragment fragment=new SiteAdditionDialogFragment();
            Bundle args=new Bundle();
            args.putSerializable(ARGS_CODE,uuid);
            fragment.setArguments(args);
            return fragment;
        }
    }
}