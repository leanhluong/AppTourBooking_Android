package com.example.apptourbooking.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apptourbooking.DAO.UserDAO;
import com.example.apptourbooking.Domain.PhieuGiamGia;
import com.example.apptourbooking.Domain.UserInfo;
import com.example.apptourbooking.R;

import java.util.List;

public class ManagerAccountAdapter extends BaseAdapter {

    private Context context;
    private List<UserInfo> userList;

    UserDAO userDAO;
    public ManagerAccountAdapter(Context context, List<UserInfo> userList) {
        this.context = context;
        this.userList = userList;
    }

    @Override
    public int getCount() {
        return userList==null?0: userList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View viewUser;
        if(convertView == null){
            viewUser = View.inflate(parent.getContext(), R.layout.manager_account, null);
        } else viewUser = convertView;

        UserInfo userInfo =  userList.get(position);
            TextView txt_id = (TextView) viewUser.findViewById(R.id.lv_mAccount_Id);
            TextView txt_fullname = (TextView) viewUser.findViewById(R.id.lv_mAccount_fullname);
            TextView txt_username = (TextView) viewUser.findViewById(R.id.lv_mAccount_username);
            TextView txt_password = (TextView) viewUser.findViewById(R.id.lv_mAccount_passowrd);
            TextView txt_role = (TextView) viewUser.findViewById(R.id.lv_mAccount_role);

            txt_id.setText("" + userInfo.getUserId());
            txt_fullname.setText( userInfo.getFullName());
            txt_username.setText(userInfo.getUserName());
            txt_password.setText(userInfo.getPassword());
            txt_role.setText(""+ userInfo.getRole());
            return viewUser;

//        ViewHolder holder;
//        if(convertView == null){
//            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            convertView = inflater.inflate(R.layout.manager_account, null);
//            holder =  new ManagerAccountAdapter.ViewHolder();
//            //ánh xạ
//            holder.txt_id = (TextView) convertView.findViewById(R.id.lv_mAccount_Id);
//            holder.txt_fullname = (TextView) convertView.findViewById(R.id.lv_mAccount_fullname);
//            holder.txt_username = (TextView) convertView.findViewById(R.id.lv_mAccount_username);
//            holder.txt_password = (TextView) convertView.findViewById(R.id.lv_mAccount_passowrd);
//            holder.txt_role = (TextView) convertView.findViewById(R.id.lv_mAccount_role);
//            convertView.setTag(holder);
//        }
//        else {
//            holder = (ManagerAccountAdapter.ViewHolder) convertView.getTag();
//        }
//        //gán giá trị
//        UserInfo userInfo =  userList.get(position);
//        holder.txt_id.setText(userInfo.getUserId());
//        holder.txt_fullname.setText(userInfo.getFullName());
//        holder.txt_username.setText(userInfo.getUserName());
//        holder.txt_password.setText(userInfo.getPassword());
//        holder.txt_role.setText(userInfo.getRole());
//
//
//        return convertView;
    }

    private class ViewHolder{
        TextView txt_id, txt_fullname, txt_username, txt_password, txt_role;
    }
}
