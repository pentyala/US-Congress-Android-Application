package com.pentyala.hwfinal;

import com.pentyala.hwfinal.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Aditya Kiran on 11/26/2016.
 */

public class FavoritesData {

    public static List<DataItem> legisFav;
    static List<String> legisSide;
    public static List<BillDataItem> billFav;
    public static List<ComDataItem> commFav;

    public static int legisSize,billSize,commSize;

    public static List<DataItem> legis;
    public static List<BillDataItem> bill;
    public static List<ComDataItem> comm;


    public FavoritesData()
    {
        legis=legisFav;
        bill=billFav;
        comm=commFav;
        if(legis==null)
            legisSize=0;
        else {
            legisSize = legis.size();
            Sort.sortLeg(legis,2);
        }

        if(bill==null)
            billSize=0;
        else {
            billSize = bill.size();
            Sort.sortBill(bill);
        }

        if(comm==null)
            commSize=0;
        else {
            commSize = comm.size();
            Sort.sortComm(comm);
        }
    }

    static {
        legisFav=null;
        billFav=null;
        commFav=null;
        legisSide=new ArrayList<>();
    }

    public static void addFavLeg(DataItem di)
    {
        if(legisFav==null)
            legisFav=new ArrayList<DataItem>();
        legisFav.add(di);
        if(!legisSide.contains(di.getLast_name().charAt(0)+""))
        {
            legisSide.add(di.getLast_name().charAt(0)+"");
            Collections.sort(legisSide);
        }
    }

    public static void addFavBill(BillDataItem di)
    {
        if(billFav==null)
            billFav=new ArrayList<BillDataItem>();
        billFav.add(di);
    }

    public static void addFavCom(ComDataItem di)
    {
        if(commFav==null)
            commFav=new ArrayList<ComDataItem>();
        commFav.add(di);
    }

    public static boolean isFavoriteLegislator(DataItem item)
    {
        if(legisFav==null)
            return false;
        if(legisFav.contains(item))
            return true;
        return false;
    }

    public static boolean isFavoriteCommittee(ComDataItem item)
    {
        if(commFav==null)
            return false;
        if(commFav.contains(item))
            return true;
        return false;
    }

    public static boolean isFavoriteBill(BillDataItem item)
    {
        if(billFav==null)
            return false;
        if(billFav.contains(item))
            return true;
        return false;
    }

    public static void removeFav(DataItem item)
    {
        System.out.println(legisFav);
        System.out.println(legisSize);
        legisFav.remove(item);
        legisSize--;
        List<String> l=new ArrayList<>();
        for(int i=0;i<legisSize;i++)
        {
            System.out.println(legisFav.get(i).getLast_name().charAt(0)+"");
            if(!l.contains(legisFav.get(i).getLast_name().charAt(0)+""))
            {
                l.add(legisFav.get(i).getLast_name().charAt(0)+"");
            }
            legisSide=l;
            Collections.sort(legisSide);

        }
    }

    public static void removeFav(BillDataItem item)
    {
        billFav.remove(item);
        billSize--;
    }

    public static void removeFav(ComDataItem item)
    {
        commFav.remove(item);
        commSize--;
    }

}