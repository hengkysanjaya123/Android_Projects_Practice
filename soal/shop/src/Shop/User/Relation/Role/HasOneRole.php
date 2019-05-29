<?php
/**
 * Created by IntelliJ IDEA.
 * User: komalasurya
 * Date: 17/12/18
 * Time: 23.55
 */

namespace Shop\User\Relation\Role;


use Illuminate\Database\Eloquent\Relations\HasOne;
use Pandawa\Component\Ddd\RelationshipBehaviorTrait;
use Shop\User\Model\Role;

trait HasOneRole
{
    use RelationshipBehaviorTrait;

    public function role(): HasOne
    {
        return $this->hasOne(Role::class);
    }
}