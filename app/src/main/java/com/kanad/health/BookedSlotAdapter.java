package com.kanad.health;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class BookedSlotAdapter extends RecyclerView.Adapter<BookedSlotAdapter.MyViewHolder> {
    static String u2;
    static String bookingid1;
    static Context context;
    ArrayList<GetBookedDoctorInfo> list;

    public BookedSlotAdapter(Context context, ArrayList<GetBookedDoctorInfo> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.bookedslot,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        GetBookedDoctorInfo getBookedDoctorInfo = list.get(position);
        String u1;
        if (FirebaseAuth.getInstance().getCurrentUser()!=null){
            u1 = FirebaseAuth.getInstance().getCurrentUser().getUid().substring(0,5);
        }else{
            u1 = GoogleSignIn.getLastSignedInAccount(context).getId().substring(0,5);
        }
        u2 = getBookedDoctorInfo.getUid().substring(5,10);
        bookingid1 = u1+u2;
        holder.bookingid.setText(bookingid1);
        holder.slot_Timing.setText(getBookedDoctorInfo.getSlot());
        holder.starttime.setText(getBookedDoctorInfo.getStarttime());
        holder.doctornameonslot.setText(getBookedDoctorInfo.getDoctorname_());
        holder.copybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("MeetingID",holder.bookingid.getText());
                clipboard.setPrimaryClip(clip);
                Snackbar snackbar = Snackbar.make(context, view, "Meeting ID Copied!", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView bookingid,slot_Timing,starttime,doctornameonslot;
        Button intenttovideocall;
        ImageView copybutton;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            bookingid = itemView.findViewById(R.id.bookingid);
            copybutton = itemView.findViewById(R.id.copybutton);
            slot_Timing = itemView.findViewById(R.id.slot_Timing);
            starttime = itemView.findViewById(R.id.starttime);
            intenttovideocall = itemView.findViewById(R.id.intenttovideocall);
            doctornameonslot = itemView.findViewById(R.id.doctornameonslot1);
            intenttovideocall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,VideoCallActivity.class);
                    intent.putExtra("MeetingID", bookingid.getText());
                    context.startActivity(intent);
                }
            });
        }
    }
}
