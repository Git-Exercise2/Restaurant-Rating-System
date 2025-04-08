package io.renren.modules.demo.controller;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.annotation.LogOperation;
import io.renren.common.config.Constants;
import io.renren.common.constant.Constant;
import io.renren.common.page.PageData;
import io.renren.common.utils.ExcelUtils;
import io.renren.common.utils.Result;
import io.renren.common.validator.AssertUtils;
import io.renren.common.validator.ValidatorUtils;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.DefaultGroup;
import io.renren.common.validator.group.UpdateGroup;
import io.renren.modules.demo.dao.RestaurantDao;
import io.renren.modules.demo.dto.RestaurantDTO;
import io.renren.modules.demo.dto.YelpRestaurant;
import io.renren.modules.demo.dto.YelpReviews;
import io.renren.modules.demo.entity.DiscussEntity;
import io.renren.modules.demo.entity.RestaurantEntity;
import io.renren.modules.demo.excel.RestaurantExcel;
import io.renren.modules.demo.service.DiscussService;
import io.renren.modules.demo.service.RestaurantService;
import io.renren.modules.security.password.PasswordUtils;
import io.renren.modules.sys.dto.SysUserDTO;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.enums.SuperAdminEnum;
import io.renren.modules.sys.service.SysRoleUserService;
import io.renren.modules.sys.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.junit.experimental.categories.Categories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import jakarta.servlet.http.HttpServletResponse;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("demo/restaurant")
@Tag(name = "")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Resource
    private RestaurantDao restaurantDao;

    @Resource
    private DiscussService discussService;

    @Resource
    private SysUserService sysUserService;

    @Resource
    private SysRoleUserService sysRoleUserService;

    @GetMapping("page")
    public Result<PageData<RestaurantDTO>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> params) {
        PageData<RestaurantDTO> page = restaurantService.page(params);

        return new Result<PageData<RestaurantDTO>>().ok(page);
    }

    @GetMapping("all")
    public Result getAll(@Parameter(hidden = true) @RequestParam Map<String, Object> params) {
        if (params.containsKey("userId")){
            return new Result<>().ok(restaurantDao.selectListByStar(Long.parseLong(String.valueOf(params.get("userId")))));
        }
        if (params.containsKey("key") && params.get("key") != null){
            return new Result<>().ok(restaurantDao.selectListByKey("%" + params.get("key") + "%"));
        }
        return new Result<>().ok(restaurantDao.selectList(new QueryWrapper<>()));
    }

    @GetMapping("star")
    public Result getAll(@RequestParam("userId") Long userId, @RequestParam("restaurantId") Long restaurantId, @RequestParam(required = false, name = "clickType") Integer clickType) {
        int count = restaurantDao.selectStar(userId, restaurantId);
        if (clickType != null) {
            //collect
            if (clickType == 0) {
                if (count > 0) {
                    return new Result().error("Already Star");
                } else {
                    restaurantDao.insertStr(userId, restaurantId);
                    return new Result().ok(1);
                }
            }
            if (clickType == 1) {
                if (count == 0) {
                    return new Result().error("Can not cancel star");
                } else {
                    restaurantDao.removeStar(userId, restaurantId);
                    return new Result().ok(0);
                }
            }
        }
        return new Result().ok(count > 0 ? 1 : 0);
    }

    @GetMapping("/removeStar")
    public Result removeStar(@RequestParam(name = "userId") Long userId, @RequestParam(name = "restaurantId") Long restaurantId){
        restaurantDao.removeStar(userId, restaurantId);
        return new Result();
    }


    @GetMapping("{id}")
    public Result<RestaurantDTO> get(@PathVariable("id") Long id) {
        RestaurantDTO data = restaurantService.get(id);

        return new Result<RestaurantDTO>().ok(data);
    }

    @PostMapping
    public Result save(@RequestBody RestaurantDTO dto) {
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        restaurantService.save(dto);

        return new Result();
    }

    @PostMapping("saveYelp")
    public Result saveYelp(@RequestBody RestaurantDTO dto) {
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
        long count = restaurantDao.selectCount(new QueryWrapper<RestaurantEntity>().eq("name", dto.getName()).eq("address", dto.getAddress()));
        if (count > 0){
            return new Result().error("Already Saved !");
        }
        restaurantService.save(dto);

        SysUserDTO user = sysUserService.getByUsername(dto.getName());

        if (user != null) {
            return new Result().error("Account already exists");
        }

        SysUserEntity entity = new SysUserEntity();
        entity.setUsername(dto.getName());
        entity.setRealName(dto.getName());
        entity.setPassword(PasswordUtils.encode("123456"));
        entity.setSuperAdmin(SuperAdminEnum.NO.value());
        entity.setGender(0);
        entity.setStatus(1);
        sysUserService.insert(entity);

        //save user and role
        List<Long> rolesIds = new ArrayList<>();
        rolesIds.add(1895834925999017985L);
        sysRoleUserService.saveOrUpdate(entity.getId(), rolesIds);

        return new Result();
    }

    @PutMapping
    public Result update(@RequestBody RestaurantDTO dto) {
        //Validity data
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        restaurantService.update(dto);

        return new Result();
    }

    @DeleteMapping
    public Result delete(@RequestBody Long[] ids) {
        //Validity data
        AssertUtils.isArrayEmpty(ids, "id");

        restaurantService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    public void export(@Parameter(hidden = true) @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<RestaurantDTO> list = restaurantService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, "", list, RestaurantExcel.class);
    }

    @GetMapping("queryYelpApi")
    public Result queryYelpApi(String location, String attributes) {
        if (location == null || location.isEmpty()){
            return new Result().error("location is empty");
        }
        String url = Constants.YELP_QUERY_API + "&location=" + location;
        if (attributes != null && !attributes.isEmpty()){
            url = url + "&attributes=" + attributes;
        }
        HttpResponse httpResponse = HttpUtil.createGet(url)
                .bearerAuth(Constants.API_KEY)
                .execute();
        if (httpResponse == null) {
            return new Result().error("yelp Api invoke failed !");
        }
        String response = httpResponse.body();
        if (response == null || response.isEmpty()) {
            return new Result().error("yelp Api invoke failed !");
        }
        try {
            YelpRestaurant yelpRestaurant = JSONUtil.toBean(response, YelpRestaurant.class);
            if (yelpRestaurant == null){
                return new Result().error("yelp Api invoke failed !");
            }
            List<RestaurantEntity> restaurantEntities = new ArrayList<>();
            yelpRestaurant.getBusinesses().forEach(businessesDTO -> {
                try {
                    RestaurantEntity restaurantEntity = new RestaurantEntity();
                    restaurantEntity.setName(businessesDTO.getName());
                    restaurantEntity.setAddress(businessesDTO.getLocation().getAddress1());
                    restaurantEntity.setLatitude(String.valueOf(businessesDTO.getCoordinates().getLatitude()));
                    restaurantEntity.setLongitude(String.valueOf(businessesDTO.getCoordinates().getLongitude()));
                    restaurantEntity.setRating(String.valueOf(businessesDTO.getRating()));
                    restaurantEntity.setReviewCount(businessesDTO.getReviewCount());
                    restaurantEntity.setPriceLevel(businessesDTO.getPrice());
                    restaurantEntity.setCategories(businessesDTO.getCategories().stream()
                            .map(YelpRestaurant.BusinessesDTO.CategoriesDTO::getTitle)
                            .filter(Objects::nonNull)
                            .collect(Collectors.joining(", ")));
                    restaurantEntity.setAttr1(JSONUtil.toJsonStr(businessesDTO.getCategories()));
                    restaurantEntity.setAttr2(businessesDTO.getId());
                    restaurantEntity.setImageUrl(businessesDTO.getImageUrl());
                    restaurantEntity.setUrl(businessesDTO.getUrl());
                    restaurantEntity.setPhone(businessesDTO.getPhone());
                    restaurantEntity.setCreateDate(new Date());
                    restaurantEntities.add(restaurantEntity);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            return new Result().ok(restaurantEntities);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result().error("yelp Api invoke failed !");
        }
    }

}
