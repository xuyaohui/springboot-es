//package cn.momosv.es.controller;
//
//import cn.momosv.es.entity.GoodInfo;
//import cn.momosv.es.repository.GoodRepository;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
//import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
//import org.springframework.data.elasticsearch.core.query.SearchQuery;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//import java.util.Optional;
//
///**
// * @author linshengwen
// * @version 1.0
// * @description
// * @date 2018/9/26 10:15
// **/
//@RestController
//public class GoodController {
//
//    @Autowired
//    private GoodRepository goodRepository;
//
//    //http://localhost:8888/save
//    @GetMapping("save")
//    public String save(){
//        GoodInfo goodInfo = new GoodInfo(System.currentTimeMillis(),
//                "商品"+System.currentTimeMillis(),"这是一个测试商品");
//        goodRepository.save(goodInfo);
//        return "success";
//    }
//
////    //http://localhost:8888/delete?id=1525415333329
////    @GetMapping("delete")
////    public String delete(long id){
////        goodRepository.delete(id);
////        return "success";
////    }
////
////    //http://localhost:8888/update?id=1525417362754&name=修改&description=修改
////    @GetMapping("update")
////    public String update(long id,String name,String description){
////        GoodInfo goodInfo = new GoodInfo(id,
////                name,description);
////        goodRepository.save(goodInfo);
////        return "success";
////    }
////
//    //http://localhost:8888/getOne?id=1525417362754
//    @GetMapping("getOne")
//    public GoodInfo getOne(long id){
//        Optional<GoodInfo> goodInfo = goodRepository.findById(id);
//       return goodInfo.get();
//       // return goodInfo;
//    }
////
////
////    //每页数量
////    private Integer PAGESIZE=10;
////
////    //http://localhost:8888/getGoodList?query=商品
////    //http://localhost:8888/getGoodList?query=商品&pageNumber=1
////    //根据关键字"商品"去查询列表，name或者description包含的都查询
////    @GetMapping("getGoodList")
////    public List<GoodInfo> getList(Integer pageNumber, String query){
////        if(pageNumber==null) pageNumber = 0;
////        //es搜索默认第一页页码是0
////        SearchQuery searchQuery=getEntitySearchQuery(pageNumber,PAGESIZE,query);
////        Page<GoodInfo> goodPage = goodRepository.search(searchQuery);
////        return goodPage.getContent();
////    }
////
////
////    private SearchQuery getEntitySearchQuery(int pageNumber, int pageSize, String searchContent) {
////        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery()
////                .add(QueryBuilders.matchPhraseQuery("name", searchContent),
////                        ScoreFunctionBuilders.weightFactorFunction(100))
////                .add(QueryBuilders.matchPhraseQuery("description", searchContent),
////                        ScoreFunctionBuilders.weightFactorFunction(100))
////                //设置权重分 求和模式
////                .scoreMode("sum")
////                //设置权重分最低分
////                .setMinScore(10);
////
////        // 设置分页
////        Pageable pageable = new PageRequest(pageNumber, pageSize);
////        return new NativeSearchQueryBuilder()
////                .withPageable(pageable)
////                .withQuery(functionScoreQueryBuilder).build();
////    }
//
//}
//
