package example.com.sousuodome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.greenrobot.greendao.query.Query;

import java.util.ArrayList;
import java.util.List;

import example.com.sousuodome.greendao.SelectGreenDaoBean;
import example.com.sousuodome.adapter.LiShiSouSuoAdapter;
import example.com.sousuodome.adapter.ReSouAdapter;
import example.com.sousuodome.greendao.DaoSession;
import example.com.sousuodome.greendao.SelectGreenDaoBeanDao;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mIvBack;
    /**
     * 食品饮料 美味到尖叫
     */
    private EditText mSsedit;
    /**
     * 搜索
     */
    private Button mSsbutton;
    /**
     * 清空历史搜索
     */
    private Button mClearbtn;
    private RecyclerView mResou;
    private RecyclerView mLishisousuo;
    private List<String> strlist = new ArrayList<>();
    private LiShiSouSuoAdapter liShiSouSuoAdapter;
    private SelectGreenDaoBeanDao selectGreenDaoBeanDao;
    private Query<SelectGreenDaoBean> userQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaoSession daoSession = ((MyApp) getApplication()).getDaoSession();
        selectGreenDaoBeanDao = daoSession.getSelectGreenDaoBeanDao();
        initView();
        initData();
        userQuery = selectGreenDaoBeanDao.queryBuilder().orderAsc(SelectGreenDaoBeanDao.Properties.Id).build();
        List<SelectGreenDaoBean> daoList = queryList();
        for (int i = 0; i < daoList.size(); i++) {
            strlist.add(daoList.get(i).getSelectGoods());
        }
    }

    private void initView() {
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mIvBack.setOnClickListener(this);
        mSsedit = (EditText) findViewById(R.id.ssedit);
        mSsbutton = (Button) findViewById(R.id.ssbutton);
        mSsbutton.setOnClickListener(this);
        mClearbtn = (Button) findViewById(R.id.clearbtn);
        mClearbtn.setOnClickListener(this);
        mResou = (RecyclerView) findViewById(R.id.resou);
        mLishisousuo = (RecyclerView) findViewById(R.id.lishisousuo);
    }

    private void initData() {

        List<String> horizontallist = new ArrayList<>();

        horizontallist.add("衬衣");
        horizontallist.add("韩版短裤");
        horizontallist.add("9分裤 新品");
        horizontallist.add("青年套装");
        horizontallist.add("外星人笔记本");
        horizontallist.add("小汽车");

        ReSouAdapter reSouAdapter = new ReSouAdapter(MainActivity.this, horizontallist);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        mResou.setLayoutManager(linearLayoutManager);
        mResou.setAdapter(reSouAdapter);

        liShiSouSuoAdapter = new LiShiSouSuoAdapter(MainActivity.this, strlist);
        LinearLayoutManager tManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, true);
        mLishisousuo.setLayoutManager(tManager);
        mLishisousuo.setAdapter(liShiSouSuoAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                this.finish();
                break;
            case R.id.ssbutton:
                String itemGoods = mSsedit.getText().toString().trim();
                if (itemGoods.isEmpty()) {
                    Toast.makeText(MainActivity.this, "亲,没有搜索的东东呢!!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                //保存搜索历史到数据库
                SelectGreenDaoBean bean = new SelectGreenDaoBean(null, "1775", "TheScar", itemGoods);
                selectGreenDaoBeanDao.insert(bean);
                liShiSouSuoAdapter.notifyDataSetChanged();
                Intent intent = new Intent(MainActivity.this,LieBiaoActivity.class);
                intent.putExtra("goodsName",itemGoods);
                startActivity(intent);
                break;
            case R.id.clearbtn:
                strlist.clear();
                deleteUser();
                liShiSouSuoAdapter.notifyDataSetChanged();
                break;
        }
    }
    //查询全部的数据
    private List<SelectGreenDaoBean> queryList() {
        List<SelectGreenDaoBean> users = userQuery.list();
        return users;
    }
    //删除所有
    private void deleteUser() {
        selectGreenDaoBeanDao.deleteAll();
    }
}
