package com.boco.whl.funddemo.widgets;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import com.boco.whl.funddemo.R;


public class BaseCloseDialog extends Dialog {
    private Context context;

    public BaseCloseDialog(Context context) {
        super(context, R.style.MyDialog);
        this.context = context;
        this.setCancelable(false);
    }

    public static class Builder {
        private View contentView;
        private Context context;
        private String contentStr;
        private String titleStr;
        private OnClickListener cancelListener;
        private OnClickListener okListener;
        private boolean isOk = false;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setMessage(String message) {
            this.contentStr = message;
            return this;
        }

        /**
         * Set the Dialog message from resource
         *
         * @param message
         * @return
         */
        public Builder setMessage(int message) {
            this.contentStr = (String) context.getText(message);
            return this;
        }

        public Builder setContentView(View v) {
            this.contentView = v;
            return this;
        }

        public Builder isOK(boolean ok) {
            this.isOk = ok;
            return this;
        }

        public Builder setTitleStr(int str) {
            this.titleStr = (String) context.getText(str);
            return this;
        }

        public Builder setTitleStr(String str) {
            this.titleStr = str;
            return this;
        }

        public Builder setCloseBtn(boolean isshow) {
            return this;
        }

        public Builder setCancleButton(OnClickListener listener) {
            this.cancelListener = listener;
            return this;
        }

        public Builder setokButton(OnClickListener listener) {
            this.okListener = listener;
            return this;
        }

        public BaseCloseDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final BaseCloseDialog dialog = new BaseCloseDialog(context);
            View layout = inflater.inflate(R.layout.activity_base_dialog, null);
            ((TextView) layout.findViewById(R.id.base_dialog_content_tv)).setText(contentStr);
            ((TextView) layout.findViewById(R.id.base_dialog_title_tv)).setText(titleStr);
            layout.findViewById(R.id.base_dialog_cancle_btn).setVisibility(View.VISIBLE);
            if (isOk) {
                layout.findViewById(R.id.base_dialog_ok_btn).setVisibility(View.VISIBLE);
            } else {
                layout.findViewById(R.id.base_dialog_ok_btn).setVisibility(View.GONE);
            }

            dialog.addContentView(layout, new LayoutParams(
                    LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));

            if (cancelListener != null) {
                layout.findViewById(R.id.base_dialog_cancle_btn)
                        .setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                cancelListener.onClick(dialog,
                                        DialogInterface.BUTTON_POSITIVE);
                            }
                        });
            }

            if (okListener != null) {
                layout.findViewById(R.id.base_dialog_ok_btn)
                        .setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                okListener.onClick(dialog,
                                        DialogInterface.BUTTON_POSITIVE);
                            }
                        });
            }
            dialog.setContentView(layout);
            return dialog;
        }
    }

}
