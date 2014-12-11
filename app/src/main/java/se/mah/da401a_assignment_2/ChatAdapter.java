package se.mah.da401a_assignment_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ksango on 01/12/14.
 */
public class ChatAdapter extends ArrayAdapter<ChatMessage> {

  LayoutInflater mLayoutInflater;

  public ChatAdapter(Context context, ArrayList<ChatMessage> data) {
    super(context, R.layout.row_group, data);
    mLayoutInflater = LayoutInflater.from(context);
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    ViewHolder holder = null;

    if (convertView == null) {
      holder = new ViewHolder();

      convertView = mLayoutInflater.inflate(R.layout.row_chat, parent, false);

      holder.from = (TextView) convertView.findViewById(R.id.row_chat_from);
      holder.message = (TextView) convertView.findViewById(R.id.row_chat_message);
      holder.time = (TextView) convertView.findViewById(R.id.row_chat_time);

      convertView.setTag(holder);
    } else {
      holder = (ViewHolder) convertView.getTag();
    }

    holder.from.setText(getItem(position).getFrom());
    holder.message.setText(getItem(position).getMessage());
    holder.time.setText(getItem(position).getTime());

    return convertView;
  }

  private class ViewHolder {
    TextView from;
    TextView message;
    TextView time;
  }
}
