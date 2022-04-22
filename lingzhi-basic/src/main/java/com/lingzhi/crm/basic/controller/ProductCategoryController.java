package com.lingzhi.crm.basic.controller;

import java.util.LinkedList;
import java.util.List;

import com.lingzhi.crm.basic.vo.TreeSelect;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lingzhi.crm.common.annotation.Log;
import com.lingzhi.crm.common.core.controller.BaseController;
import com.lingzhi.crm.common.core.domain.AjaxResult;
import com.lingzhi.crm.common.enums.BusinessType;
import com.lingzhi.crm.basic.domain.ProductCategory;
import com.lingzhi.crm.basic.service.IProductCategoryService;
import com.lingzhi.crm.common.utils.poi.ExcelUtil;
import com.lingzhi.crm.common.core.page.TableDataInfo;

/**
 * 产品类别信息Controller
 * 
 * @author ruoyi
 * @date 2021-06-01
 */
@RestController
@RequestMapping("/basic/product/category")
public class ProductCategoryController extends BaseController
{
    @Autowired
    private IProductCategoryService productCategoryService;

    /**
     * 查询产品类别信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:category:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProductCategory productCategory)
    {
        startPage();
        List<ProductCategory> list = productCategoryService.selectProductCategoryList(productCategory);
        return getDataTable(list);
    }

    /**
     * 导出产品类别信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:category:export')")
    @Log(title = "产品类别信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ProductCategory productCategory)
    {
        List<ProductCategory> list = productCategoryService.selectProductCategoryList(productCategory);
        ExcelUtil<ProductCategory> util = new ExcelUtil<ProductCategory>(ProductCategory.class);
        return util.exportExcel(list, "产品类别信息数据");
    }

    /**
     * 获取产品类别信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:category:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(productCategoryService.selectProductCategoryById(id));
    }

    /**
     * 新增产品类别信息
     */
    @PreAuthorize("@ss.hasPermi('system:category:add')")
    @Log(title = "产品类别信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProductCategory productCategory)
    {
        return toAjax(productCategoryService.insertProductCategory(productCategory));
    }

    /**
     * 修改产品类别信息
     */
    @PreAuthorize("@ss.hasPermi('system:category:edit')")
    @Log(title = "产品类别信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProductCategory productCategory)
    {
        return toAjax(productCategoryService.updateProductCategory(productCategory));
    }

    /**
     * 删除产品类别信息
     */
    @PreAuthorize("@ss.hasPermi('system:category:remove')")
    @Log(title = "产品类别信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(productCategoryService.deleteProductCategoryByIds(ids));
    }

    /**
     * 查询产品类别树形结构
     * @return
     */
    @GetMapping("/categoryTree")
    public TableDataInfo getCategoryTreeList(){

        List<TreeSelect> treeSelectList = productCategoryService.getCategoryTreeList();
        return getDataTable(treeSelectList);
    }
}
