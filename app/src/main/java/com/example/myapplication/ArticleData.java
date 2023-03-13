package com.example.myapplication;


import android.content.Context;
import android.os.AsyncTask;
import android.widget.GridView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class ArticleData extends AsyncTask<String,String,String> {
    private Context context;
    private GridView gridView;
    public static  ArticleList data;

    public  ArticleData(Context context, GridView gridView){
        this.context = context;
        this.gridView = gridView;
    }
    public  static Article getPhotoFromId(int id){
        for(int i =0;i<data.getArticles().size();i++){
            if(data.getArticles().get(i).getArticle_id() == id){
                return data.getArticles().get(i);
            }
        }
        return null;
    }

    @Override
    protected String doInBackground(String... strings) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            URL url = new URL("https://fontkeyboard.org/wsv/?json_name=articles");
            connection = (HttpURLConnection) url.openConnection();
            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while((line = reader.readLine())!=null){
                buffer.append(line +"\n");
            }
            return buffer.toString();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try{
                if(connection!=null){
                    connection.disconnect();
                }
                if (reader !=null){
                    reader.close();
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Gson gson = new Gson();
        data = gson.fromJson(s,(Type) ArticleList.class);
        ArticleAdapter adapter = new ArticleAdapter(data.getArticles(),context);
        gridView.setAdapter(adapter);
    }
    //    public static ArrayList<Article> generatePhoto(){
//        ArrayList<Article> photos = new ArrayList<>();
////        photos.add(new Photo(0
////                ,"https://upload.wikimedia.org/wikipedia/commons/thumb/e/e3/Oranges_-_whole-halved-segment.jpg/330px-Oranges_-_whole-halved-segment.jpg",
////                "Orange (fruit)\n","An orange is a fruit of various citrus species in the family Rutaceae (see list of plants known as orange); it primarily refers to Citrus × sinensis,[1] which is also called sweet orange, to distinguish it from the related Citrus × aurantium, referred to as bitter orange. The sweet orange reproduces asexually (apomixis through nucellar embryony); varieties of sweet orange arise through mutations.[2][3][4][5]"));
////        photos.add(new Photo(1,"https://upload.wikimedia.org/wikipedia/commons/thumb/d/de/Bananavarieties.jpg/330px-Bananavarieties.jpg",
////                "Banana",
////                "A banana is an elongated, edible fruit – botanically a berry[1][2] – produced by several kinds of large herbaceous flowering plants in the genus Musa.[3] In some countries, bananas used for cooking may be called \"plantains\", distinguishing them from dessert bananas. The fruit is variable in size, color, and firmness, but is usually elongated and curved, with soft flesh rich in starch covered with a rind, which may be green, yellow, red, purple, or brown when ripe. The fruits grow upward in clusters near the top of the plant. Almost all modern edible seedless (parthenocarp) bananas come from two wild species – Musa acuminata and Musa balbisiana. The scientific names of most cultivated bananas are Musa acuminata, Musa balbisiana, and Musa × paradisiaca for the hybrid Musa acuminata × M. balbisiana, depending on their genomic constitution. The old scientific name for this hybrid, Musa sapientum, is no longer used.\n" +
////                        "\n"));
////        photos.add(new Photo(2
////                ,"https://upload.wikimedia.org/wikipedia/commons/thumb/e/e4/P1030323.JPG/330px-P1030323.JPG",
////                "Lemon"
////                ,"he lemon (Citrus limon) is a species of small evergreen trees in the flowering plant family Rutaceae, native to Asia, primarily Northeast India (Assam), Northern Myanmar or China.[2]\n" +
////                "\n" +
////                "The tree's ellipsoidal yellow fruit is used for culinary and non-culinary purposes throughout the world, primarily for its juice, which has both culinary and cleaning uses.[2] The pulp and rind are also used in cooking and baking. The juice of the lemon is about 5% to 6% citric acid, with a pH of around 2.2, giving it a sour taste. The distinctive sour taste of lemon juice makes it a key ingredient in drinks and foods such as lemonade and lemon meringue pie."));
////        photos.add(new Photo(3
////                ,"https://upload.wikimedia.org/wikipedia/commons/thumb/4/47/Taiwan_2009_Tainan_City_Organic_Farm_Watermelon_FRD_7962.jpg/330px-Taiwan_2009_Tainan_City_Organic_Farm_Watermelon_FRD_7962.jpg",
////                "Watermelon"
////                ,"Watermelon (Citrullus lanatus) is a flowering plant species of the Cucurbitaceae family and the name of its edible fruit. A scrambling and trailing vine-like plant, it is a highly cultivated fruit worldwide, with more than 1,000 varieties.\n" +
////                "\n" +
////                "Watermelon is grown in favorable climates from tropical to temperate regions worldwide for its large edible fruit, which is a berry with a hard rind and no internal divisions, and is botanically called a pepo. The sweet, juicy flesh is usually deep red to pink, with many black seeds, although seedless varieties exist. The fruit can be eaten raw or pickled, and the rind is edible after cooking. It may also be consumed as a juice or an ingredient in mixed beverages."));
////        photos.add(new Photo(4,"https://upload.wikimedia.org/wikipedia/commons/thumb/a/a6/Pink_lady_and_cross_section.jpg/330px-Pink_lady_and_cross_section.jpg",
////                "Apple",
////                "An apple is an edible fruit produced by an apple tree (Malus domestica). Apple trees are cultivated worldwide and are the most widely grown species in the genus Malus. The tree originated in Central Asia, where its wild ancestor, Malus sieversii, is still found today. Apples have been grown for thousands of years in Asia and Europe and were brought to North America by European colonists. Apples have religious and mythological significance in many cultures, including Norse, Greek, and European Christian tradition."));
//        return photos;
//    }


}
