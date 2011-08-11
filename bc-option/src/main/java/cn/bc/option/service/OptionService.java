package cn.bc.option.service;

import java.util.List;
import java.util.Map;

import cn.bc.option.domain.OptionGroup;
import cn.bc.option.domain.OptionItem;

/**
 * 选项Service接口
 * 
 * @author dragon
 * 
 */
public interface OptionService {
	/**
	 * 获取已配置的分类列表
	 * 
	 * @return
	 */
	List<OptionGroup> findOptionGroup();

	/**
	 * 查找指定分类的选项条目列表
	 * 
	 * @param optionGroupValue
	 *            所属分类，对应OptionGroup属性key的值
	 * @return
	 */
	List<OptionItem> findOptionItemByGroupKey(String optionGroupKey);

	/**
	 * 查找指定分类的选项条目列表,如果返回的结果中不存在对应currentItemKey的OptionItem项，会自动添加该项到列表的最后
	 * <p>
	 * 如果currentItemKey为null，将被忽略不作处理
	 * </p>
	 * <p>
	 * 如果currentItemValue为null，将设置为等于currentItemKey的值
	 * </p>
	 * 
	 * @param optionGroupKey
	 *            所属分类，对应OptionGroup属性key的值
	 * @param currentItemKey
	 *            当前选项的key
	 * @param currentItemValue
	 *            当前选项的value
	 * @return
	 */
	List<OptionItem> findOptionItemByGroupKeyWithCurrent(String optionGroupKey,
			String currentItemKey, String currentItemValue);

	/**
	 * 查找指定分类的选项条目列表
	 * 
	 * @param optionGroupKeys
	 *            所属分类，对应OptionGroup属性Key的值
	 * @return 返回结果的key为对应的OptionGroup的Key值，value为对应该key的OptionItem的列表
	 */
	Map<String, List<OptionItem>> findOptionItemByGroupKeys(
			String[] optionGroupKeys);
}