package com.codedleaf.sylveryte.myemployeeattendanceregister;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by sylveryte on 12/6/16.
 */
public class SiteAdditionFragment extends Fragment {


    private EditText mEditText_siteName;
    private EditText mEditText_description;
    private Button mAddButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.site_addition_fragment,container,false);


        mEditText_siteName=(EditText)v.findViewById(R.id.editText_site_name);
        mEditText_description=(EditText)v.findViewById(R.id.editText_site_description);


        mAddButton = (Button)v.findViewById(R.id.button_add_site);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //// TODO: 12/6/16 clean all this
                Site s=new Site();
                s.setTitle(mEditText_siteName.getText().toString());
                s.setDescription(mEditText_description.getText().toString());
                SitesLab.getInstanceOf().addSite(s);

            }
        });

        return v;
    }

    public static SiteAdditionFragment createInstance()
    {
        return new SiteAdditionFragment();
    }
}
