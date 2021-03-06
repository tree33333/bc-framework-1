package cn.bc.identity.dao;

import java.util.List;
import java.util.Map;

import cn.bc.core.dao.CrudDao;
import cn.bc.identity.domain.Actor;
import cn.bc.identity.domain.ActorHistory;
import cn.bc.identity.domain.Resource;

/**
 * 参与者Dao接口
 * 
 * @author dragon
 * 
 */
public interface ActorDao extends CrudDao<Actor> {
	/**
	 * 根据编码获取，如用户的登录名
	 * 
	 * @param actorCode
	 *            actor的编码
	 * @return
	 */
	Actor loadByCode(String actorCode);

	/**
	 * 获取从属方的单一上级
	 * 
	 * @param followerId
	 *            从属方id
	 * @param masterTypes
	 *            主控方的类型，对应Actor的type属性
	 * @return
	 */
	Actor loadBelong(Long followerId, Integer[] masterTypes);

	/**
	 * 获取从属方的所有上级
	 * 
	 * @param followerId
	 *            从属方id
	 * @param masterTypes
	 *            主控方的类型，对应Actor的type属性
	 * @return
	 */
	List<Actor> findBelong(Long followerId, Integer[] masterTypes);

	/**
	 * 获取从属方指定关联关系的主控方
	 * 
	 * @param followerId
	 *            从属方id
	 * @param relationTypes
	 *            关联的类型，对应ActorRelation的type属性
	 * @param masterTypes
	 *            主控方的类型，对应Actor的type属性
	 * @return
	 */
	List<Actor> findMaster(Long followerId, Integer[] relationTypes,
			Integer[] masterTypes);

	/**
	 * 获取隶属指定actor的所有actor
	 * 
	 * @param masterId
	 *            主控方id
	 * @param relationTypes
	 *            关联的类型，对应ActorRelation的type属性
	 * @param followerTypes
	 *            从属方的类型，对应Actor的type属性
	 * @return
	 */
	List<Actor> findFollower(Long masterId, Integer[] relationTypes,
			Integer[] followerTypes);

	/**
	 * 获取隶属指定actor的所有子actor
	 * 
	 * @param masterId
	 *            主控方id
	 * @param relationTypes
	 *            关联的类型，对应ActorRelation的type属性
	 * @param followerTypes
	 *            从属方的类型，对应Actor的type属性
	 * @param followerName
	 *            从属方的名称
	 * @param followerStatuses
	 *            从属方的状态，对应Actor的status属性
	 * @return
	 */
	List<Actor> findFollowerWithName(Long masterId, String followerName,
			Integer[] relationTypes, Integer[] followerTypes,
			Integer[] followerStatuses);

	/**
	 * 获取顶层单位信息
	 * 
	 * @return
	 */
	List<Actor> findTopUnit();

	/**
	 * 获取隶属关系中的所有直接下级组织信息
	 * 
	 * @param higherOrganizationId
	 *            上级组织的id
	 * @param lowerOrganizationTypes
	 *            下级组织的类型，对应Actor的type属性，默认为单位+部门+岗位
	 * @return
	 */
	List<Actor> findLowerOrganization(Long higherOrganizationId,
			Integer... lowerOrganizationTypes);

	/**
	 * 获取隶属关系中的所有直接上级组织信息
	 * 
	 * @param lowerOrganizationId
	 *            下级组织的id
	 * @param higherOrganizationTypes
	 *            上级组织的类型，对应Actor的type属性，默认为单位+部门+岗位
	 * @return
	 */
	List<Actor> findHigherOrganization(Long lowerOrganizationId,
			Integer... higherOrganizationTypes);

	/**
	 * 获取直接隶属于组织的人员信息
	 * 
	 * @param organizationId
	 *            上级组织的id
	 * @return
	 */
	List<Actor> findUser(Long organizationId);

	/**
	 * 获取隶属关系中的所有上级组织信息，包括上级的上级
	 * 
	 * @param lowerOrganizationId
	 *            下级组织的id
	 * @param ancestorOrganizationTypes
	 *            上级组织的类型，对应Actor的type属性，默认为单位+部门+岗位
	 * @return
	 */
	List<Actor> findAncestorOrganization(Long lowerOrganizationId,
			Integer... ancestorOrganizationTypes);

	/**
	 * 获取隶属关系中的所有下级组织信息，包括下级的下级
	 * 
	 * @param higherOrganizationId
	 *            上级组织的id
	 * @param descendantOrganizationTypes
	 *            下级组织的类型，对应Actor的type属性，默认为单位+部门+岗位
	 * @return
	 */
	List<Actor> findDescendantOrganization(Long higherOrganizationId,
			Integer... descendantOrganizationTypes);

	/**
	 * 获取隶属于组织的人员信息，包括组织的所有后代组织下的人员
	 * 
	 * @param organizationId
	 *            上级组织的id
	 * @param descendantOrganizationTypes
	 *            下级组织的类型，对应Actor的type属性，默认为单位+部门+岗位
	 * @return
	 */
	List<Actor> findDescendantUser(Long organizationId,
			Integer... descendantOrganizationTypes);

	/**
	 * 获取actor可以使用的模块列表
	 * 
	 * @param actorId
	 * @return
	 */
	List<Resource> findCanUseModules(Long actorId);

	/**
	 * 获取所有单位信息
	 * 
	 * @param statues
	 *            状态列表
	 * @return
	 */
	List<Actor> findAllUnit(Integer... statues);

	/**
	 * 处理组织间的隶属关系，这里只处理隶属的组织是部门或单位的情况
	 * 
	 * @param follower
	 * @param belongId
	 */
	Actor save4belong(Actor follower, Long belongId);

	/**
	 * 处理组织间的隶属关系，这里只处理隶属的组织是部门或单位的情况
	 * 
	 * @param follower
	 * @param belongIds
	 */
	Actor save4belong(Actor follower, Long[] belongIds);

	/**
	 * 获取指定类型和状态的Actor
	 * 
	 * @param actorTypes
	 *            类型列表
	 * @param actorStatues
	 *            状态列表
	 * @return
	 */
	List<Actor> find(Integer[] actorTypes, Integer[] actorStatues);

	/**
	 * 获取指定类型和状态的Actor
	 * 
	 * @param actorTypes
	 *            类型列表
	 * @param actorStatues
	 *            状态列表
	 * @return
	 */
	List<ActorHistory> findHistory(Integer[] actorTypes, Integer[] actorStatues);

	/**
	 * 获取指定类型和状态的Actor信息
	 * 
	 * @param actorTypes
	 *            类型列表
	 * @param actorStatues
	 *            状态列表
	 * @return 返回结果中的元素Map格式为：
	 *         <ul>
	 *         <li>id -- Actor的id</li>
	 *         <li>type -- Actor的type</li>
	 *         <li>code -- Actor的code</li>
	 *         <li>name -- Actor的name</li>
	 *         <li>pcode -- Actor的pcode</li>
	 *         <li>pname -- Actor的pname</li>
	 *         </ul>
	 */
	List<Map<String, String>> find4option(Integer[] actorTypes,
			Integer[] actorStatues);

	/**
	 * 获取指定类型和状态的ActorHistory信息
	 * 
	 * @param actorTypes
	 *            类型列表
	 * @param actorStatues
	 *            状态列表
	 * @return 返回结果中的元素Map格式为：
	 *         <ul>
	 *         <li>id -- ActorHistory的id</li>
	 *         <li>aid -- Actor的id</li>
	 *         <li>type -- Actor的type</li>
	 *         <li>code -- Actor的code</li>
	 *         <li>name -- Actor的name</li>
	 *         <li>pcode -- ActorHistory的pcode</li>
	 *         <li>pname -- ActorHistory的pname</li>
	 *         </ul>
	 */
	List<Map<String, String>> findHistory4option(Integer[] actorTypes,
			Integer[] actorStatues);

	/**
	 * 根据用户帐号获取用户的姓名
	 * 
	 * @param userCode
	 *            用户帐号
	 * @return
	 */
	String loadActorNameByCode(String actorCode);
}
