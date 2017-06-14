package com.jxkj.readapp.ioc.component;

import android.app.Activity;

import com.jxkj.readapp.ioc.scope.ActivityScope;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent
public interface ActivityComponent {
    
//    FragmentComponent fragmentComponent();
//
//    /**--------------首页----------------*/
//    void inject(MallNearByShowActivity mallNearByShowActivity);
//    void inject(GeneralizeEarnV1Activity generalizeEarnV1Activity);
//
//
//    /**--------------极客----------------*/
//    void inject(TopicDetailActivity topicDetailActivity);
//    void inject(TopicAllCommentActivity topicAllCommentActivity);
//    void inject(TopicReplyCommentActivity topicReplyCommentActivity);
//    void inject(PublishTopicActivity publishTopicActivity);
//
//
//    /**--------------商城----------------*/
//    void inject(MallGeoActivity mallGeoTestActivity);
//    void inject(MallSearchActivity mallSearchActivity);
//    void inject(MallHotProActivityV2 mallHotProActivityV2);


    void injectaaa(Activity activity);
}