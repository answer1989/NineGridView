# NineGridView
类似微信朋友圈的9宫格图片控件

效果图：
![](http://7q5er6.com1.z0.glb.clouddn.com/62CA3A9A-F518-441D-9C95-C76C49D0172D.png)

使用方式

```java

NineGridView nineGridView = (NineGridView)findViewById(R.id.nine_grid_view);
NineGridAdapter nineGridAdapter = new DemoNineGridAdapter(MainActivity.this, generateDemoList(new Random().nextInt(10)));
nineGridView.setNineGridAdapter(nineGridAdapter);


private List<String> generateDemoList(int size) {
        List<String> imageUrl = new ArrayList<>();
        int count;
        if (size <= 0) {
            count = 1;
        } else {
            count = size;
        }
        for (int i = 0; i < count; i++) {
            imageUrl.add("http://img5.duitang.com/uploads/item/201306/24/20130624230830_UWTWS.thumb.700_0.jpeg");
        }
        return imageUrl;
    }

public class DemoNineGridAdapter extends NineGridAdapter<String> {

    private List<String> imageList;
    private Context context;

    public DemoNineGridAdapter(Context context, List<String> imageList) {
        this.imageList = imageList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public int getItemLayoutRes(@LayoutRes int position) {
        if (imageList.size() == 1) {
            return R.layout.item_view_big_image;
        } else {
            return R.layout.item_view_normal;
        }

    }

    @Override
    public void onBindItemView(int position, View view) {
        ImageView imageView = (ImageView) view.findViewById(R.id.image_view);
        Glide.with(context).load(getItem(position)).placeholder(R.color.colorAccent).into(imageView);
    }

    @Override
    public String getItem(int position) {
        return imageList.get(position);
    }
}
```