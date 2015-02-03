// --------- GroupsDaoHandler --------- //
(function($) {

	var defaultOpts = {
		idName : "id"
	};

	/**
	 * Create a GroupsDaoHandler type
	 *
	 * Note: since it is a in memory store, all the dao function return entity clone object to make sure to avoid
	 *       the user to inadvertently change a stored entity.
	 *
	 * @param {String} entityType. create a table for dao with the Entity type (e.g., "User", "Task", or "Project").
	 * @param {Array} seed. Seed the store. Array of object with their id (if not, uuid will be generated)
	 * @param {Object} opts. Options for this
	 *                   opts.idName {String} the property name of the id value (default "id")
	 */
	function GroupsDaoHandler(entityType,seed, opts) {
		init.call(this,entityType,seed, opts);
	}


	function init(entityType,seed,opts) {
		this._entityType = entityType;
		this._opts = $.extend({}, defaultOpts, opts);
		this._idName = this._opts.idName;

		initData.call(this, seed);
	}

	function initData(seed) {
		// ._dataDic is the dictionary for all the data as {id:obj} (not that the obj also has the obj[id] value
		var dic = this._dataDic = {};
		var idName = this._idName;

		if ($.isArray(seed)) {
			$.each(seed, function(idx, val) {
				var id = val[idName];
				if ( typeof id === "undefined") {
					id = brite.uuid();
					val[idName] = id;
				}
				dic[id] = val;
			});
		}

	}
	
	// --------- DAO Info Methods --------- //
	GroupsDaoHandler.prototype.entityType = function() {
		return this._entityType;
	};
	// --------- DAO Info Methods --------- //

	// --------- DAO Interface Implementation --------- //
	/**
	 * DAO Interface. Return value directly since it is in memory.
	 * @param {Integer} id
	 * @return the entity
	 */
	GroupsDaoHandler.prototype.get = function(id) {
		
		var entity = this._dataDic[id];
		if (entity) {
			return $.extend({}, entity);
		} else {
			return entity;
		}
	};

	/**
	 * DAO Interface: Create new object, set new id, and add it.
	 *
	 * @param {Object} newEntity if null, does nothing (TODO: needs to throw exception)
	 */
	GroupsDaoHandler.prototype.create = function(newEntity) {
		if (newEntity) {
			var newId = brite.uuid();
			newEntity[this._idName] = newId;
			this._dataDic[newId] = newEntity;
		}

		return $.extend({}, newEntity);
	};

	/**
	 * DAO Interface: remove an instance of objectType for a given type and id.
	 *
	 * Return the id deleted
	 *
	 * @param {Integer} id
	 *
	 */
	GroupsDaoHandler.prototype["delete"] = function(id) {
		var entity = this._dataDic[id];
		if (entity) {
			delete this._dataDic[id];
		}
		return id;
	};

	/**
	 * Additional methods to remove multiple items
	 * 
	 * @param {Array} ids. Array of entities id that needs to be removed 
	 * 
	 * @return the array of ids that have been removed
	 */
	GroupsDaoHandler.prototype.deleteMany = function(ids){
		var that = this;
		
		// TODO: need to check if the entity exists and 
		//       return only the list of ids that have been removed
		$.each(ids,function(idx,val){
			delete that._dataDic[val];
		});
		
		return ids;
	};

	GroupsDaoHandler.prototype.batchDelete = function(match){
		var dao = this;
		var deletedIds = [];
		$.each(this._dataDic, function(key, entity) {
			if (passMatch(entity,match)){
				delete dao._dataDic[key];
				deletedIds.push(key);				
			}
		});
		return deletedIds;
	};
	
	/**
	 * DAO Interface: update a existing id with a set of property/value data.
	 *
	 * The DAO resolve with the updated data.
	 *
	 * @param {Object} data Object containing the id and the properties to be updated
	 *
	 * Return the new object data
	 */
	GroupsDaoHandler.prototype.update = function(data) {
		var id = data[this._idName];
		if (typeof id === "undefined"){
			throw "BRITE ERROR: GroupsDaoHandler.update: data does not have an id property. Cannot update.";	
		}
		var entity = this._dataDic[id];
		if (entity) {
			// make sure to remove the idName value TODO: need to check and throw error if not match
			delete data[this._idName];
			$.extend(entity, data);
			return $.extend({}, entity);
		} else {
			return null;
		}
	};
	
	/**
	 * Batch update given a match criteria
	 * 
	 * @param {object} data the object with the prop:value to update
	 * @param {object} (optional) match matching object as in list, delete. If not spe
	 * 
	 */
	GroupsDaoHandler.prototype.batchUpdate = function(data, match){
		var dao = this;
		var updatedList = [];
		// make sure to remove any accidental .id property of the data
		delete data[this._idName];
		// update all the matching records
		$.each(this._dataDic, function(key, entity) {
			if (passMatch(entity,match)){
				$.extend(entity, data);
				updatedList.push($.extend({}, entity));				
			}
		});
		return updatedList;
	};
		

	/**
	 * DAO Interface: Return a deferred object for this objectType and options
	 * @param {Object} opts
	 *           opts.pageIndex {Number} Index of the page, starting at 0.
	 *           opts.pageSize  {Number} Size of the page
	 *           opts.match     {Object} Object of matching items. If item is a single value, then, it is a ===, 
	 *																		Note implemented: advanced operation support with an object, with a .op for operation
	 *																		{age:{op:">",val:12}} (will match any item where .age > 12)
	 *																		if opts.match is an array, each block will be considered as OR block
	 *           opts.orderBy   {String}
	 *           opts.orderType {String} "asc" or "desc"
	 */
	GroupsDaoHandler.prototype.list = function(opts) {
        return $.ajax({
            type : "GET",
            url : "/api.php?type=g",
            dataType : "json"
        }).pipe(function(val) {
            var resultSet = val;

            if (opts) {
                if (opts.orderBy) {
                    resultSet = brite.util.array.sortBy(resultSet, opts.orderBy)
                }
            }
            return resultSet;
        });
	};
	
	/**
	 * @param {Object} match (optional) matching object as the list, and batchDelete, batchUpdate. Optional
	 */
	GroupsDaoHandler.prototype.count = function(match){
		
		var val = 0;
		$.each(this._dataDic, function(key, entity) {
			if (passMatch(entity,match)){
				val++;
			}
		});
		
		return val;
	};
	

	// --------- /DAO Interface Implementation --------- //
	
	/**
	 * 
	 * Utility method that return if an object pass the matching rule. If "match" is null or undefined, always return true;
	 * 
	 * @param {Object} obj The object to compare
	 * @param {Object} match The comparator, on the format propName:value. 
	 *									if "value" string, number, or boolean which will get compared a ===
	 *									NOT SUPPORTED YET: if "value" is an object with propName:{op:operation,value:value}, where op is the operation to campare ("<", ">", "%")
	 *									NOT SUPPORTED YET: Also, match can be an array of object, in this case, each item in the array will be OR block 
	 *								(i.e. [{age:12},{age:13}] would match any person of age 12 or 13)	
	 * 
	 */
	function passMatch(obj,match){
		var pass = true, k;
		if (match){
			for (k in match) {
				if (obj[k] !== match[k]) {
					pass = false;
					break;
				}
			}
		}
		return pass;		
	}

	brite.GroupsDaoHandler = GroupsDaoHandler;

})(jQuery);
// --------- /GroupsDaoHandler --------- //