package com.jxkj.readapp.util;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.CheckBox;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.StackingBehavior;


/**
 * Created by Administrator on 2016/12/14.
 */

public class DialogUtil {

    private static final String ENSURE = "确定";
    private static final String CANCEL = "取消";
    private static final String CLEAR = "清除";
    private static final int ICON = 0;

    private static DialogUtil mInstance;
    /**
     * 1.autoDismiss(boolean flag) 是否自动消失
     * 2.alwaysCallInputCallback()
     * 3.alwaysCallMultiChoiceCallback()
     * 4.alwaysCallSingleChoiceCallback()
     * 以上方法调用后会执行某个View的监听
     * 没有调用并且设置了callback , 那么只会在点击按钮后执行callBack
     * */
    private MaterialDialog mDialog;

    private DialogUtil(){}

    public static DialogUtil getInstance(){
        synchronized(DialogUtil.class){
            if(mInstance==null){
                mInstance=new DialogUtil();
            }
        }
        return mInstance;
    }

    public void basicNoTitle(Context context , String content , String ensure , String cancel){
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context);
        if (mDialog != null && mDialog.isShowing()){
            dismiss();
        }

        builder.content(content)
                .positiveText(ensure)
                .negativeText(cancel);

        mDialog = builder.build();

        mDialog.show();
    }

    public void basicNoTitle(Context context , String content){
        basicNoTitle(context , content , ENSURE , CANCEL);
    }

    public void basic(Context context , String title , String content , String ensure , String cancel){
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context);
        if (mDialog != null && mDialog.isShowing()){
            dismiss();
        }

        builder.title(title)
                .content(content)
                .positiveText(ensure)
                .negativeText(cancel)
        ;
        mDialog = builder.build();
        mDialog.show();
    }
    public void basic(Context context , String title , String content){
        basic(context , title , content , ENSURE , CANCEL);
    }

    public void showBasicIcon(Context context , int resId , String title , String content , String ensure , String cancel){
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context);
        if (mDialog != null && mDialog.isShowing()){
            dismiss();
        }
        builder.iconRes(resId)
                .limitIconToDefaultSize() // limits the displayed icon size to 48dp
                .title(title)
                .content(content)
                .positiveText(ensure)
                .negativeText(cancel)
        ;
        mDialog = builder.build();
        mDialog.show();
    }

    public void showBasicCallback(Context context , MaterialDialog.SingleButtonCallback callback , String content , String ensure , String cancel){
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context);
        if (mDialog != null && mDialog.isShowing()){
            dismiss();
        }
        builder.content(content)
                .onPositive(callback)
                .positiveText(ensure)
                .negativeText(cancel)
        ;
        mDialog = builder.build();
        mDialog.show();
    }

    public void showBasicIcon(Context context , int resId , String title , String content){
        showBasicIcon(context , resId , title , content , ENSURE , CANCEL);
    }
    public void showBasicIcon(Context context , String title , String content ){
        showBasicIcon(context , ICON, title , content);
    }

    public void showBasicCheckPrompt(Context context , int resId , String title
            , String ensure , String cancel , String checkBoxText , boolean initiallyChecked
            , MaterialDialog.SingleButtonCallback callback , CheckBox.OnCheckedChangeListener onCheckedChangeListener) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context);
        if (mDialog != null && mDialog.isShowing()){
            dismiss();
        }
        builder.iconRes(resId)
                .limitIconToDefaultSize()
                .title(title)
                .positiveText(ensure)
                .negativeText(cancel)
                .onAny(callback)
                .checkBoxPrompt(checkBoxText, initiallyChecked, onCheckedChangeListener)
        ;
        mDialog = builder.build();
        mDialog.show();
    }
    public void showBasicCheckPrompt(Context context , int resId , String title
            , String checkBoxText, MaterialDialog.SingleButtonCallback callback) {
        showBasicCheckPrompt(context,resId,title,ENSURE, CANCEL
                ,checkBoxText,false,callback,null);
    }

    public void showBasicCheckPrompt(Context context , String title
            , String checkBoxText, MaterialDialog.SingleButtonCallback callback) {
        showBasicCheckPrompt(context,ICON,title,ENSURE, CANCEL
                ,checkBoxText,false,callback,null);
    }

    public void showStacked(Context context , String title , String content
            , String ensure , String cancel , GravityEnum gravityEnum
            , StackingBehavior stackingBehavior) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context);
        if (mDialog != null && mDialog.isShowing()){
            dismiss();
        }
        builder.title(title)
                .content(content)
                .positiveText(ensure)
                .negativeText(cancel)
                .btnStackedGravity(gravityEnum)
                .stackingBehavior(stackingBehavior)  // this generally should not be forced, but is used for demo purposes
        ;
        mDialog = builder.build();
        mDialog.show();
    }

    public void showStacked(Context context , String title , String content
            , GravityEnum gravityEnum, StackingBehavior stackingBehavior) {
        showStacked(context , title , content , ENSURE , CANCEL
                , GravityEnum.END , StackingBehavior.ALWAYS);
    }

    public void showNeutral(Context context , String title , String content
            , String ensure , String cancel , String neutralText) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context);
        if (mDialog != null && mDialog.isShowing()){
            dismiss();
        }
        builder.title(title)
                .content(content)
                .positiveText(ensure)
                .negativeText(cancel)
                .neutralText(neutralText)
        ;
        mDialog = builder.build();
        mDialog.show();
    }

    public void showNeutral(Context context , String title , String content,
                            String neutralText) {
        showNeutral(context , title , content , ENSURE , CANCEL , neutralText);
    }

    /**
     * DialogAction which
     * 根据 Enum DialogAction 判断点击了哪个Button 点击后自动消失
     * */
    public void showNeutralCallbacks(Context context , String title , String content
            , String ensure , String cancel , String neutralText , MaterialDialog.SingleButtonCallback
                                             callback) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context);
        if (mDialog != null && mDialog.isShowing()){
            dismiss();
        }
        builder.title(title)
                .content(content)
                .positiveText(ensure)
                .negativeText(cancel)
                .neutralText(neutralText)
                .onAny(callback);
        mDialog = builder.build();
        mDialog.show();
    }
    public void showNeutralCallbacks(Context context , String title , String content
            , String neutralText , MaterialDialog.SingleButtonCallback
                                             callback) {
        showNeutralCallbacks(context , title , content , ENSURE , CANCEL , neutralText , callback);
    }

    /**
     * callBack:
     *      int which:点击的下标
     *      CharSequence text: 点击的 items 的名字
     *
     * */
    public void showList(Context context , String title
            , MaterialDialog.ListCallback callback ,
                         CharSequence...items) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context);
        if (mDialog != null && mDialog.isShowing()){
            dismiss();
        }
        builder.title(title)
                .items(items)
                .itemsCallback(callback);
        mDialog = builder.build();
        mDialog.show();
    }
    public void showList(Context context , String title
            , MaterialDialog.ListCallback callback ,
                         int itemsRes) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context);
        if (mDialog != null && mDialog.isShowing()){
            dismiss();
        }
        builder.title(title)
                .items(itemsRes)
                .itemsCallback(callback);
        mDialog = builder.build();
        mDialog.show();
    }

    public void showListNoTitle(Context context, MaterialDialog.ListCallback callback ,
                                int itemsRes) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context);
        if (mDialog != null && mDialog.isShowing()){
            dismiss();
        }
        builder.items(itemsRes)
                .itemsCallback(callback);
        mDialog = builder.build();
        mDialog.show();
    }
    public void showListNoTitle(Context context, MaterialDialog.ListCallback callback ,
                                CharSequence...items) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context);
        if (mDialog != null && mDialog.isShowing()){
            dismiss();
        }
        builder.items(items)
                .itemsCallback(callback);
        mDialog = builder.build();
        mDialog.show();
    }
    public void showSingleChoice(Context context, String title, int defaultSelect
            , MaterialDialog.ListCallbackSingleChoice callBack
            , String ensure , CharSequence... items) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context);
        if (mDialog != null && mDialog.isShowing()){
            dismiss();
        }
        builder.title(title)
                .items(items)
                .itemsCallbackSingleChoice(defaultSelect, callBack)
                .positiveText(ensure).widgetColor(Color.parseColor("#f40e47"));

        mDialog = builder.build();
        mDialog.show();
    }
    public void showSingleChoice(Context context, String title, int defaultSelect
            , MaterialDialog.ListCallbackSingleChoice callBack
            , String ensure , int itemsRes) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context);
        if (mDialog != null && mDialog.isShowing()){
            dismiss();
        }
        builder.title(title)
                .items(itemsRes)
                .itemsCallbackSingleChoice(defaultSelect, callBack)
                .positiveText(ensure);

        mDialog = builder.build();
        mDialog.show();
    }
    public void showSingleChoice(Context context, String title
            , MaterialDialog.ListCallbackSingleChoice callBack
            , int itemsRes) {
        showSingleChoice(context , title , 0 , callBack , ENSURE , itemsRes);
    }
    public void showSingleChoice(Context context, String title
            , MaterialDialog.ListCallbackSingleChoice callBack
            , CharSequence... items) {
        showSingleChoice(context , title , 0 , callBack , ENSURE , items);
    }


    /**
     *
     * @param context
     * @param title
     * @param selectedIndices   默认选中的选项下标
     * @param multiChoiceCallBack   CheckBox的监听 , 返回值boolean 是否可选中
     * @param neutralTextCallback   中立按钮监听
     * @param ensure
     * @param neutralText
     * @param itemsRes
     */
    public void showMultiChoice(Context context , String title , Integer[] selectedIndices
            , MaterialDialog.ListCallbackMultiChoice multiChoiceCallBack , MaterialDialog.SingleButtonCallback neutralTextCallback
            , String ensure , String neutralText , int itemsRes) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context);
        if (mDialog != null && mDialog.isShowing()){
            dismiss();
        }
        builder.title(title)
                .items(itemsRes)
                .itemsCallbackMultiChoice(selectedIndices, multiChoiceCallBack)
                .onNeutral(neutralTextCallback)
                .alwaysCallMultiChoiceCallback()
                .positiveText(ensure)
                .autoDismiss(false)
                .neutralText(neutralText);
        mDialog = builder.build();
        mDialog.show();
    }

    public void showMultiChoice(Context context , String title , Integer[] selectedIndices
            , MaterialDialog.ListCallbackMultiChoice multiChoiceCallBack , MaterialDialog.SingleButtonCallback neutralTextCallback
            , String ensure , String neutralText , CharSequence... items) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context);
        if (mDialog != null && mDialog.isShowing()){
            dismiss();
        }
        builder.title(title)
                .items(items)
                .itemsCallbackMultiChoice(selectedIndices, multiChoiceCallBack)
                .onNeutral(neutralTextCallback)
                .alwaysCallMultiChoiceCallback()
                .positiveText(ensure)
                .autoDismiss(false)
                .neutralText(neutralText);
        mDialog = builder.build();
        mDialog.show();
    }

    /**
     * 默认第一个选中 , 中立按钮文字CLEAR , 中立按钮点击 清除选择
     * */
    public void showMultiChoice(Context context , String title
            , MaterialDialog.ListCallbackMultiChoice multiChoiceCallBack
            , CharSequence... items) {
        showMultiChoice(context , title , new Integer[0] , multiChoiceCallBack , new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                dialog.clearSelectedIndices();
            }
        }, ENSURE , CLEAR , items);
    }
    public void showMultiChoice(Context context , String title
            , MaterialDialog.ListCallbackMultiChoice multiChoiceCallBack
            , int itemsRes) {
        showMultiChoice(context , title , new Integer[0] , multiChoiceCallBack , new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                dialog.clearSelectedIndices();
            }
        }, ENSURE , CLEAR , itemsRes);
    }
    public void showMultiChoiceDisabledItems(Context context , String title , Integer[] selectedIndices
            , MaterialDialog.ListCallbackMultiChoice multiChoiceCallBack
            , String ensure , int itemsRes , Integer...disableItems) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context);
        if (mDialog != null && mDialog.isShowing()){
            dismiss();
        }
        builder.title(title)
                .items(itemsRes)
                .itemsCallbackMultiChoice(selectedIndices, multiChoiceCallBack)
                .alwaysCallMultiChoiceCallback()
                .positiveText(ensure)
                .autoDismiss(false)
                .itemsDisabledIndices(disableItems);
        mDialog = builder.build();
        mDialog.show();
    }
    public void showMultiChoiceDisabledItems(Context context , String title
            , MaterialDialog.ListCallbackMultiChoice multiChoiceCallBack
            , int itemsRes , Integer...disableItems) {
        showMultiChoiceDisabledItems(context , title , new Integer[1] , multiChoiceCallBack , ENSURE , itemsRes , disableItems);
    }
//    /**
//     * ex:  final MaterialSimpleListAdapter adapter = new MaterialSimpleListAdapter(new MaterialSimpleListAdapter.Callback() {
//     public void onMaterialListItemSelected(MaterialDialog dialog, int index, MaterialSimpleListItem item) {
//     showToast(item.getContent().toString());
//     }
//     });
//     adapter.add(new MaterialSimpleListItem.Builder(this)
//     .content("username@gmail.com")
//     .icon(R.drawable.ic_account_circle)
//     .backgroundColor(Color.WHITE)
//     .build());
//     adapter.add(new MaterialSimpleListItem.Builder(this)
//     .content(R.string.add_account)
//     .icon(R.drawable.ic_content_add)
//     .iconPaddingDp(8)
//     .build());
//     * */
//    public void showSimpleList(Context context , String title ,  MaterialSimpleListAdapter adapter) {
//        MaterialDialog.Builder builder = new MaterialDialog.Builder(context);
//        if (mDialog != null && mDialog.isShowing()){
//            dismiss();
//        }
//        builder.title(title)
//                .adapter(adapter, null);
//        mDialog = builder.build();
//        mDialog.show();
//    }

    public void showCustomList(Context context , String title , RecyclerView.Adapter<?> adapter) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context);
        if (mDialog != null && mDialog.isShowing()){
            dismiss();
        }
        builder.title(title)
                .adapter(adapter, null);
        mDialog = builder.build();
        mDialog.show();
    }

    public MaterialDialog showCustomView(Context context, int view) {
         MaterialDialog dialog = new MaterialDialog.Builder(context).customView(view,false).show();
            return  dialog;

    }
//    public void showBasicCustomView(Context context, View.OnClickListener listener,String title ,String rightText) {
//        final MaterialDialog dialog = new MaterialDialog.Builder(context).customView(R.layout.group_basic_dialog,false).show();
//        View customView = dialog.getCustomView();
//        TextView tv_title = (TextView) customView.findViewById(R.id.tv_title);
//        TextView tv_right = (TextView) customView.findViewById(R.id.tv_basic_yes);
//        TextView tv_left = (TextView) customView.findViewById(R.id.tv_basic_no);
//        tv_title.setText(title);
//        tv_right.setText(rightText);
//        tv_right.setOnClickListener(listener);
//        tv_left.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//
//    }

//    public void showCustomView() {
//        MaterialDialog dialog = new MaterialDialog.Builder(this)
//                .title(R.string.googleWifi)
//                .customView(R.layout.dialog_customview, true)
//                .positiveText(R.string.connect)
//                .negativeText(android.R.string.cancel)
//                .onPositive(new MaterialDialog.SingleButtonCallback() {
//                    @Override
//                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                        showToast("Password: " + passwordInput.getText().toString());
//                    }
//                }).build();
//
//        positiveAction = dialog.getActionButton(DialogAction.POSITIVE);
//        //noinspection ConstantConditions
//        passwordInput = (EditText) dialog.getCustomView().findViewById(R.id.password);
//        passwordInput.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                positiveAction.setEnabled(s.toString().trim().length() > 0);
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//            }
//        });
//
//        // Toggling the show password CheckBox will mask or unmask the password input EditText
//        CheckBox checkbox = (CheckBox) dialog.getCustomView().findViewById(R.id.showPassword);
//        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                passwordInput.setInputType(!isChecked ? InputType.TYPE_TEXT_VARIATION_PASSWORD : InputType.TYPE_CLASS_TEXT);
//                passwordInput.setTransformationMethod(!isChecked ? PasswordTransformationMethod.getInstance() : null);
//            }
//        });
//
//        int widgetColor = ThemeSingleton.get().widgetColor;
//        MDTintHelper.setTint(checkbox,
//                widgetColor == 0 ? ContextCompat.getColor(this, R.color.accent) : widgetColor);
//
//        MDTintHelper.setTint(passwordInput,
//                widgetColor == 0 ? ContextCompat.getColor(this, R.color.accent) : widgetColor);
//
//        dialog.show();
//        positiveAction.setEnabled(false); // disabled by default
//    }

    /**
     * @param context
     * @param title
     * @param content
     * @param inputType 输入类型
     * @param minLength 最少长度
     * @param maxLength 最长长度
     * @param ensure
     * @param hint  提示信息
     * @param prefill   预写信息
     * @param allowEmptyInput   是否input为空
     * @param callback  点击enSure按钮监听(和alwaysCallInputCallback()有关 这里没有调用)
     */
    public void showInputDialog(Context context, String title , String content , int inputType
            , int minLength , int maxLength , String ensure , @Nullable CharSequence hint
            , CharSequence prefill, boolean allowEmptyInput, MaterialDialog.InputCallback callback) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context);
        if (mDialog != null && mDialog.isShowing()){
            dismiss();
        }
        builder.title(title)
                .content(content)
                .inputType(inputType)
                .inputRange(minLength, maxLength)
                .positiveText(ensure)
                .input(hint, prefill, allowEmptyInput, callback);
        mDialog = builder.build();
        mDialog.show();
    }

    public void showInputDialog(Context context, String title , String content , int inputType
            , int maxLength, MaterialDialog.InputCallback callback) {
        showInputDialog(context,title,content,inputType,0,maxLength,ENSURE,"","",true,callback);
    }

//    public void showProgressDeterminateDialog() {
//        new MaterialDialog.Builder(this)
//                .title(R.string.progress_dialog)
//                .content(R.string.please_wait)
//                .contentGravity(GravityEnum.CENTER)
//                .progress(false, 150, true)
//                .cancelListener(new DialogInterface.OnCancelListener() {
//                    @Override
//                    public void onCancel(DialogInterface dialog) {
//                        if (mThread != null)
//                            mThread.interrupt();
//                    }
//                })
//                .showListener(new DialogInterface.OnShowListener() {
//                    @Override
//                    public void onShow(DialogInterface dialogInterface) {
//                        final MaterialDialog dialog = (MaterialDialog) dialogInterface;
//                        startThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                while (dialog.getCurrentProgress() != dialog.getMaxProgress() &&
//                                        !Thread.currentThread().isInterrupted()) {
//                                    if (dialog.isCancelled())
//                                        break;
//                                    try {
//                                        Thread.sleep(50);
//                                    } catch (InterruptedException e) {
//                                        break;
//                                    }
//                                    dialog.incrementProgress(1);
//                                }
//                                runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        mThread = null;
//                                        dialog.setContent(getString(R.string.md_done_label));
//                                    }
//                                });
//
//                            }
//                        });
//                    }
//                }).show();
//    }


    public void showProgressHorizontalIndeterminateDialog(Context context
            , String title , String content) {
        showIndeterminateProgressDialog(context , title , content , true);
    }

    public void showIndeterminateProgressDialog(Context context , String title , String content , boolean horizontal) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context);
        if (mDialog != null && mDialog.isShowing()){
            dismiss();
        }
        builder.title(title)
                .content(content)
                .progress(true, 0)
                .progressIndeterminateStyle(horizontal);
        mDialog = builder.build();
        mDialog.show();
    }

    public void showSimpleListNoTitle(Context context , MaterialDialog.ListCallback
            callback , CharSequence...items){
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context);
        if (mDialog != null && mDialog.isShowing()){
            dismiss();
        }
        builder
                .items(items)
                .itemsCallback(callback);
        mDialog = builder.build();
        mDialog.show();
    }


    private void dismiss(){
        mDialog.dismiss();
    }
}
